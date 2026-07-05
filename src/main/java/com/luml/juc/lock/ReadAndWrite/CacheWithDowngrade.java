package com.luml.juc.lock.ReadAndWrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author luml
 * @description
 * @date 2026/6/29
 */
public class CacheWithDowngrade {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private String data = "initial";

    public void updateAndRead() {
        // 1. 获取写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 获取写锁，开始更新数据");
            // 模拟业务逻辑：更新数据
            data = "updated_by_" + Thread.currentThread().getName();

            // 2. 获取读锁 (关键步骤：在释放写锁前获取读锁)
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " 获取读锁，准备降级");
        } finally {
            // 3. 释放写锁 (完成降级：现在只持有读锁)
            rwLock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName() + " 释放写锁，降级完成");
        }

        try {
            // 4. 执行读操作 (此时其他读线程可以并发进入，但写线程被阻塞)
            System.out.println(Thread.currentThread().getName() + " 读取数据: " + data);
            // 模拟耗时读操作
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // 5. 释放读锁
            rwLock.readLock().unlock();
            System.out.println(Thread.currentThread().getName() + " 释放读锁");
        }
    }
}

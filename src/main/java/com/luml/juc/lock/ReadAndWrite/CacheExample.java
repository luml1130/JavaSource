package com.luml.juc.lock.ReadAndWrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author luml
 * @description
 * @date 2026/6/18
 */
public class CacheExample {

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

    private String data = "Initial Data";

    //----------------------  一、读锁 ------------------------------

    //1、读操作：多个线程可同时执行
    public String readData() {
        readLock.lock();
        try {
            // 模拟读取耗时
            Thread.sleep(100);
            return data;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            readLock.unlock(); // 务必在 finally 中释放锁
        }
    }
    //2、错误的锁升级（读 -> 写）会导致死锁
    public void deadlockExample() {
        readLock.lock();
        try {
            // 读取数据
            String current = data;

            // ❌ 错误：尝试在持有读锁时获取写锁
            // 如果其他线程也持有读锁并试图升级，或者当前线程等待其他读线程释放，
            // 这将导致死锁或无限等待
            writeLock.lock();
            try {
                data = "updated";
            } finally {
                writeLock.unlock();
            }
        } finally {
            readLock.unlock();
        }
    }



    //----------------------  二、写锁 ------------------------------
    //1、 写操作：独占执行
    public void writeData(String newData) {
        writeLock.lock();
        try {
            // 模拟写入耗时
            Thread.sleep(500);
            this.data = newData;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock(); // 务必在 finally 中释放锁
        }
    }
    //2、写多重入
    public void outerMethod() {
        writeLock.lock();
        try {
            // 执行一些写操作
            innerMethod(); // 内部方法再次获取写锁，不会死锁
        } finally {
            writeLock.unlock();
        }
    }
    public void innerMethod() {
        writeLock.lock(); // 重入成功，写锁计数 +1
        try {
            // 执行更多写操作
        } finally {
            writeLock.unlock(); // 写锁计数 -1
        }
    }
    //3、锁降级（写 -> 读）
    public void downgradeExample2() {
        writeLock.lock();
        try {
            // 1. 修改数据
            data = "new value";

            // 2. 获取读锁（此时同时持有写锁和读锁）
            readLock.lock();
        } finally {
            // 3. 释放写锁（完成降级，当前线程仍持有读锁）
            // 注意：必须在 finally 中释放写锁，确保即使异常也能降级
            writeLock.unlock();
        }

        try {
            //4. 执行读操作，其他写线程仍需等待
            // 此时线程仅持有读锁，可以安全读取数据。 其他写线程仍被阻塞，但其他读线程可以并发进入
            System.out.println(data);
        } finally {
            // 5. 释放读锁
            readLock.unlock();
        }
    }
}

package com.luml.juc.lock.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luml
 * @description
 * @date 2026/7/9
 */
public class LockExample {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock(); // 默认非公平锁

    public void increment() {
        lock.lock(); // 获取锁
        try {
            count++;
        } finally {
            lock.unlock(); // 务必在 finally 中释放锁
        }
    }

    // 尝试获取锁，带超时
    public boolean tryIncrement() {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    count++;
                    return true;
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return false;
    }
}

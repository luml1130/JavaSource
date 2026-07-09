package com.luml.juc.lock.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luml
 * @description
 * @date 2026/7/9
 */
public class Counter {
    private final ReentrantLock lock = new ReentrantLock(); // 默认非公平
    private int count = 0;
    public void increment() {
        lock.lock(); // 加锁
        try {
            count++;
        } finally {
            lock.unlock(); // 必须在 finally 中解锁，防止异常导致死锁
        }
    }
    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}

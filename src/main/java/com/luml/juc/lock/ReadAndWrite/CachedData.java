package com.luml.juc.lock.ReadAndWrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author luml
 * @description
 * @date 2026/7/9
 */
public class CachedData {
    private final Map<String, String> cache = new HashMap<>();
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

    // 读操作：允许多个线程并发执行
    public String getData(String key) {
        readLock.lock();
        try {
            return cache.get(key);
        } finally {
            readLock.unlock();
        }
    }

    // 写操作：独占执行，阻塞其他读写
    public void setData(String key, String value) {
        writeLock.lock();
        try {
            cache.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    // 锁降级示例
    public String updateAndRead(String key, String newValue) {
        writeLock.lock();
        try {
            cache.put(key, newValue);
            // 获取读锁
            readLock.lock();
        } finally {
            // 释放写锁（此时仍持有读锁，完成降级）
            writeLock.unlock();
        }

        try {
            // 在读锁保护下进行后续读取或其他非修改操作
            return cache.get(key);
        } finally {
            readLock.unlock();
        }
    }
}

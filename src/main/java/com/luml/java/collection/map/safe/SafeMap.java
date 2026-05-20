package com.luml.java.collection.map.safe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author luml
 * @description
 * 自定义线程安全的map：
 *  基于 ReadWriteLock 的封装（适合读多写少）
 *  这是最常见的自定义方式。利用 ReentrantReadWriteLock，允许多个线程同时读取，但写入时独占锁，从而在高并发读取场景下提升性能。
 * @date 2026/5/20
 */
public class SafeMap<K, V>  {

    private final Map<K, V> map = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    // 获取值：使用读锁，允许多线程并发读
    public V get(K key) {
        lock.readLock().lock();
        try {
            return map.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    // 设置值：使用写锁，独占访问
    public V put(K key, V value) {
        lock.writeLock().lock();
        try {
            return map.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // 删除值
    public V remove(K key) {
        lock.writeLock().lock();
        try {
            return map.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // 注意：遍历操作也需要加锁，通常建议返回副本或在锁内处理
    public Map<K, V> snapshot() {
        lock.readLock().lock();
        try {
            return new HashMap<>(map);
        } finally {
            lock.readLock().unlock();
        }
    }
}

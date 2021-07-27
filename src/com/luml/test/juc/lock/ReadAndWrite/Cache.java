package com.luml.test.juc.lock.ReadAndWrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存示例说明读写锁的使用方式
 */
public class Cache {

    private static final Map<String, Object>    map = new HashMap<String, Object>();
    private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private static final Lock         readLock   = rwl.readLock();
    private static final Lock         writeLock  = rwl.writeLock();

    /**
     * 获取一个key对应的vale
     * @param key
     * @return
     */
    public static final Object get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    /**
     * 设置key对应的value，并返回旧的value
     * @param key
     * @param value
     * @return
     */
    public static final Object put(String key, Object value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
    /**
     * 清空所有的内容
     */
    public static final void clear() {
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }
}

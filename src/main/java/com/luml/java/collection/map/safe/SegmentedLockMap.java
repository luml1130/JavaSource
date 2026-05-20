package com.luml.java.collection.map.safe;

/**
 * @author luml
 * @description
 * 原理演示方案：手动实现分段锁 (Segment Lock)
 *          虽然 JDK 1.8 已弃用此结构，但理解它有助于掌握并发控制思想。我们将 Map 分为多个段（Segment），每个段独立加锁。
 * @date 2026/5/20
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 简易版分段锁 Map 实现
 *      注意：此实现仅用于演示分段锁原理，功能不完整（如缺少扩容、迭代器支持等），生产环境请直接使用 ConcurrentHashMap。
 *
 * 核心原理简析
 *     ‌数据分片‌：将整个 Map 的数据划分为 N 个段（Segment），每个段维护独立的 HashMap 和 ReentrantLock。
 *     ‌锁隔离‌：当线程 A 访问段 1，线程 B 访问段 2 时，它们获取的是不同的锁，因此可以并行执行，互不阻塞。
 *     ‌局限性‌：
 *         并发度受限于段数量（默认 16）。
 *         跨段操作（如 size()、containsValue()）需要获取所有段的锁，性能较差。
 *         JDK 1.8 已用更高效的 CAS + synchronized 节点锁取代了此设计。
 */
public class SegmentedLockMap<K, V> {
    // 分段数量，通常设为 2 的幂次方，如 16
    private static final int SEGMENT_COUNT = 16;

    // 内部段类，每个段包含一个 HashMap 和一把锁
    private static class Segment<K, V> {
        final ReentrantLock lock = new ReentrantLock();
        final Map<K, V> map = new HashMap<>();

        V put(K key, V value) {
            lock.lock();
            try {
                return map.put(key, value);
            } finally {
                lock.unlock();
            }
        }

        V get(K key) {
            lock.lock();
            try {
                return map.get(key);
            } finally {
                lock.unlock();
            }
        }

        // 读操作其实可以优化为不加锁或使用读写锁，这里为了简单演示统一加锁
    }

    private final Segment<K, V>[] segments;

    @SuppressWarnings("unchecked")
    public SegmentedLockMap() {
        segments = (Segment<K, V>[]) new Segment[SEGMENT_COUNT];
        for (int i = 0; i < SEGMENT_COUNT; i++) {
            segments[i] = new Segment<>();
        }
    }

    // 根据 Key 的 hashCode 确定属于哪个段
    private int getSegmentIndex(K key) {
        int hash = key.hashCode();
        // 确保索引为正数，并映射到 0-15
        return (hash & 0x7FFFFFFF) % SEGMENT_COUNT;
    }

    public V put(K key, V value) {
        int index = getSegmentIndex(key);
        return segments[index].put(key, value);
    }

    public V get(K key) {
        int index = getSegmentIndex(key);
        return segments[index].get(key);
    }

    // 测试主方法
    public static void main(String[] args) throws InterruptedException {
        SegmentedLockMap<String, Integer> segmentedMap = new SegmentedLockMap<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                segmentedMap.put("thread1-key-" + i, i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                segmentedMap.put("thread2-key-" + i, i);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("分段锁 Map 测试完成。");
        // 注意：由于没有实现 size 方法的线程安全聚合，这里不打印 size
    }
}

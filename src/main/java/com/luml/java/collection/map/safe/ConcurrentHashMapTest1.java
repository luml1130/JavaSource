package com.luml.java.collection.map.safe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author luml
 * @description
 * 在 Java 中，ConcurrentHashMap 是线程安全 Map 的典型代表。
 *      其实现机制经历了从 ‌JDK 1.7 的分段锁（Segment Lock）‌ 到 ‌JDK 1.8+ 的 CAS + synchronized‌ 的演进。
 * 由于 JDK 1.8+ 已经废弃了 Segment 分段锁结构，转而使用更细粒度的节点锁，
 *      因此现代 Java 开发中‌不再建议手动实现基于 Segment 的 Map‌。以下提供两种示例：
 *     ‌现代标准实现‌：直接使用 JDK 1.8+ ConcurrentHashMap（基于 CAS + synchronized），这是生产环境的首选。
 *          本代码
 *     ‌原理演示实现‌：手动模拟 JDK 1.7 风格的“分段锁”机制，帮助理解其核心思想（仅用于学习，不建议生产使用）。
 *          见：SegmentedLockMap
 *
 * 一、 现代标准方案：ConcurrentHashMap (CAS + synchronized)
 * JDK 1.8 之后，ConcurrentHashMap 摒弃了 Segment，采用 Node 数组 + 链表/红黑树。
 *     ‌读操作‌：无锁，利用 volatile 保证可见性。
 *     ‌写操作‌：仅锁定当前哈希桶（Bucket）的头节点。如果桶为空，使用 ‌CAS‌ 尝试插入；如果桶不为空，使用 ‌synchronized‌ 锁定头节点。
 * 示例代码
 * 核心原理简析
 *     ‌CAS (Compare-And-Swap)‌：用于在无竞争情况下原子地插入新节点到空桶中，避免加锁开销。
 *     ‌synchronized‌：当发生哈希冲突（多个键映射到同一个桶）时，只锁定该桶的头节点（链表头或红黑树根）。
 *      这比全局锁或分段锁粒度更细，并发度更高。
 * @date 2026/5/20
 */
public class ConcurrentHashMapTest1 {

    public static void main(String[] args) throws InterruptedException {
        // 创建线程安全的 ConcurrentHashMap
        // 底层实现：JDK 1.8+ 使用 CAS + synchronized 锁定单个桶节点
        ConcurrentHashMap<String, Integer> safeMap = new ConcurrentHashMap<>();

        // 创建线程池模拟高并发写入
        ExecutorService executor = Executors.newFixedThreadPool(10);

        System.out.println("开始并发写入测试...");
        long start = System.currentTimeMillis();

        IntStream.range(0, 10000).forEach(i -> {
            executor.submit(() -> {
                String key = "key-" + (i % 100); // 制造一定的哈希冲突以测试锁竞争
                // put 方法内部：
                // 1. 计算 hash
                // 2. 如果桶为空，CAS 插入新节点
                // 3. 如果桶不为空，synchronized 锁定头节点，然后遍历链表/红黑树插入或更新
                safeMap.merge(key, 1, Integer::sum);
            });
        });

        // 关闭线程池并等待任务完成
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long end = System.currentTimeMillis();
        System.out.println("写入完成。耗时: " + (end - start) + " ms");
        System.out.println("Map 大小: " + safeMap.size());

        // 验证数据一致性
        int totalSum = safeMap.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("所有值的总和 (应为10000): " + totalSum);

        /**
         * 开始并发写入测试...
         * 写入完成。耗时: 164 ms
         * Map 大小: 100
         * 所有值的总和 (应为10000): 10000
         */
    }
}

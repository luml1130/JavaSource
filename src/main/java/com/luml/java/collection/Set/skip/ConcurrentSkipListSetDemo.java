package com.luml.java.collection.Set.skip;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author luml
 * @description
 * @date 2026/5/20
 */
public class ConcurrentSkipListSetDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程安全的有序 Set
        ConcurrentSkipListSet<Integer> skipSet = new ConcurrentSkipListSet<>();

        // 模拟多线程并发插入
        ExecutorService executor = Executors.newFixedThreadPool(5);

        IntStream.range(0, 100).forEach(i -> {
            executor.submit(() -> {
                // 插入随机数，重复的值会被自动去重
                skipSet.add((int)(Math.random() * 50));
            });
        });

        executor.shutdown();
        executor.awaitTermination(1, java.util.concurrent.TimeUnit.SECONDS);

        // 输出结果：自动排序且无重复
        System.out.println("有序集合内容: " + skipSet);

        // 导航操作示例
        System.out.println("大于等于 10 的最小值: " + skipSet.ceiling(10));
        System.out.println("小于等于 20 的最大值: " + skipSet.floor(20));

        // 子集视图：获取 [10, 20] 范围内的元素
        System.out.println("子集 [10, 20]: " + skipSet.subSet(10, true, 20, true));
    }
}

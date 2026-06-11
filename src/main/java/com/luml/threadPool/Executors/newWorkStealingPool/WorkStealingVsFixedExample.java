package com.luml.threadPool.Executors.newWorkStealingPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author luml
 * @description
 * 案例三：对比传统线程池（展示负载均衡优势）
 *
 * 这个案例展示了当任务执行时间差异较大时，newWorkStealingPool 如何通过窃取机制比固定线程池更高效地利用资源。
 *
 * @date 2026/6/11
 */
public class WorkStealingVsFixedExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Testing WorkStealingPool ---");
        testPool(Executors.newWorkStealingPool(2)); // 2个线程

        System.out.println("\n--- Testing FixedThreadPool (for comparison) ---");
        testPool(Executors.newFixedThreadPool(2)); // 2个线程
    }

    private static void testPool(ExecutorService executor) throws InterruptedException {
        long start = System.currentTimeMillis();

        // 提交两个长任务和两个短任务
        // 在 FixedThreadPool 中，如果线程1先拿到长任务，线程2拿到短任务，
        // 线程2完成后会空闲，直到长任务结束。
        // 在 WorkStealingPool 中，如果实现得当且任务可拆分，或者通过内部调度，
        // 它能更好地平衡负载（注：对于不可拆分的独立Runnable，主要优势在于减少队列竞争和上下文切换开销，
        // 但对于完全独立且不可拆分的任务，窃取效果不如 RecursiveTask 明显，
        // 此处主要展示其作为通用线程池的行为）。

        // 为了更明显体现窃取，通常建议配合 ForkJoinTask 使用，
        // 但 newWorkStealingPool 也接受普通 Runnable。

        executor.submit(() -> heavyTask("Long-1", 2000));
        executor.submit(() -> heavyTask("Long-2", 2000));
        executor.submit(() -> lightTask("Short-1", 100));
        executor.submit(() -> lightTask("Short-2", 100));

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start) + " ms");
    }

    private static void heavyTask(String name, int sleepTime) {
        System.out.println(name + " started on " + Thread.currentThread().getName());
        try { TimeUnit.MILLISECONDS.sleep(sleepTime); } catch (InterruptedException e) {}
        System.out.println(name + " finished on " + Thread.currentThread().getName());
    }

    private static void lightTask(String name, int sleepTime) {
        System.out.println(name + " started on " + Thread.currentThread().getName());
        try { TimeUnit.MILLISECONDS.sleep(sleepTime); } catch (InterruptedException e) {}
        System.out.println(name + " finished on " + Thread.currentThread().getName());
    }
}

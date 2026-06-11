package com.luml.threadPool.Executors.newWorkStealingPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author luml
 * @description
 * 案例一：基础用法（提交独立任务）
 *
 * 这是最简单的使用方式，适用于提交大量互不依赖的 Runnable 或 Callable 任务。
 *
 * @date 2026/6/11
 */
public class WorkStealingBasicExample {
    public static void main(String[] args) throws InterruptedException {
        // 创建 work-stealing 线程池，默认并行度为 CPU 核心数
        ExecutorService executor = Executors.newWorkStealingPool();

        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());

        // 提交 10 个模拟计算任务
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Task " + taskId + " is running on thread: " + threadName);

                // 模拟耗时计算
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Task " + taskId + " completed by: " + threadName);
            });
        }

        // 关闭线程池并等待任务完成
        executor.shutdown();
        // 注意：由于是守护线程，如果不等待，主线程结束后程序可能直接退出
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            System.err.println("Tasks did not complete in time");
        }

        System.out.println("All tasks finished.");
    }
}

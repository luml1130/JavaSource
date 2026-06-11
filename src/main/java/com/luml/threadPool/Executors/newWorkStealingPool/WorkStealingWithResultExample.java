package com.luml.threadPool.Executors.newWorkStealingPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author luml
 * @description
 * 案例二：处理有返回值的任务（Callable）
 * 当需要获取每个任务的执行结果时，可以使用 submit 返回 Future 对象
 * @date 2026/6/11
 */
public class WorkStealingWithResultExample {
    public static void main(String[] args) {
        // 指定并行度为 4
        ExecutorService executor = Executors.newWorkStealingPool(4);
        List<Future<String>> futures = new ArrayList<>();

        // 提交多个计算任务
        for (int i = 0; i < 8; i++) {
            final int index = i;
            Future<String> future = executor.submit(() -> {
                // 模拟复杂计算
                long sum = 0;
                for (long j = 0; j < 1_000_000; j++) {
                    sum += j;
                }
                return "Task " + index + " result: " + sum + " (Thread: " + Thread.currentThread().getName() + ")";
            });
            futures.add(future);
        }

        // 获取结果
        for (Future<String> future : futures) {
            try {
                // get() 会阻塞直到任务完成
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}

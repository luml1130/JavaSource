package com.luml.threadPool.Executor.Future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author luml
 * @description
 * 在这个例子中，我们创建了一个 Callable 任务，该任务在执行后会返回一个字符串。
 * 我们使用 FutureTask 来包装这个 Callable 任务，并通过一个线程池来异步执行它。
 * 最后，我们通过调用 futureTask.get() 来获取任务的结果
 * @date 2026/6/8
 */
public class FutureTaskExample2 {
    public static void main(String[] args) {
        // 创建一个Callable任务
        Callable<String> task = () -> {
            // 模拟耗时操作
            Thread.sleep(2000);
            return "Task completed";
        };
        // 将Callable任务包装成FutureTask
        FutureTask<String> futureTask = new FutureTask<>(task);

        // 使用ExecutorService来执行FutureTask
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(futureTask);

        try {
            // 获取任务结果
            String result = futureTask.get(); // 这将阻塞直到任务完成
            System.out.println(result); // 输出: Task completed
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}

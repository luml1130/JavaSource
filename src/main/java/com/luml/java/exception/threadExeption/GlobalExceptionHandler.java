package com.luml.java.exception.threadExeption;

import java.util.concurrent.*;

/**
 * 代码说明：
 *     定义了 UncaughtExceptionHandler，用于在线程因未捕获异常终止时执行日志记录和告警。
 *     通过 ThreadFactory 将该处理器绑定到线程池创建的每个线程上，实现全局兜底。
 *     演示了 execute 提交任务时，异常如何被全局处理器捕获并打印，避免异常静默丢失。
 */
public class GlobalExceptionHandler {

    public static void main(String[] args) {
        // 1. 定义未捕获异常处理器
        Thread.UncaughtExceptionHandler handler = (thread, throwable) -> {
            System.err.println("【全局捕获】线程 " + thread.getName() + " 发生异常: " + throwable.getMessage());
            throwable.printStackTrace();
            // 在此处可添加发送告警、记录日志到ELK等操作
        };

        // 2. 自定义线程工厂，将 handler 绑定到新创建的线程
        ThreadFactory factory = r -> {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(handler);
            return t;
        };

        // 3. 创建线程池
        ExecutorService executor = new ThreadPoolExecutor(
                2, 2, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                factory
        );

        // 4. 提交一个会抛出异常的任务 (使用 execute)
        executor.execute(() -> {
            System.out.println("任务开始执行...");
            throw new RuntimeException("模拟业务异常");
        });

        // 注意：submit 提交的异常通常被 Future 吞掉，不会触发 UncaughtExceptionHandler
        // 除非在 Future.get() 时处理，或者任务内部未捕获且线程池实现特定行为
        
        executor.shutdown();
    }
}

package com.luml.juc.lock.deadLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

/**
 * @author luml
 * @description
 * 注意：这个例子中，如果线程池大小为2，任务1占用了线程1，提交任务2到线程2。任务2尝试获取DB连接。
 *      如果DB连接足够，则不会死锁。但如果DB连接只有1个，或者任务2也需要等待任务1释放某些资源，就会死锁。
 * 更典型的伪死锁是：线程池满 + 外部依赖阻塞。
 * @date 2026/7/10
 */
public class PoolExhaustionDeadlock {

    // 模拟小型线程池和连接池
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);
    private static final Semaphore dbConnections = new Semaphore(2); // 只有2个DB连接

    public static void main(String[] args) {
        // 任务1：占用DB连接，然后等待任务2的结果
        Future<?> future1 = threadPool.submit(() -> {
            try {
                dbConnections.acquire(); // 获取DB连接1
                System.out.println("任务1: 获取DB连接1");

                // 模拟业务逻辑：需要等待任务2完成才能释放DB连接
                // 这里为了模拟死锁，让任务1等待任务2
                System.out.println("任务1: 等待任务2执行...");

                // 假设任务2也需要DB连接，但此时连接池可能已满
                // 如果线程池只有2个线程，任务1占了一个，任务2占了一个
                // 如果任务1在持有DB连接的同时，调用了threadPool.submit并等待(get)，
                // 而任务2需要DB连接才能开始执行，就会死锁。

                Future<?> future2 = threadPool.submit(() -> {
                    try {
                        dbConnections.acquire(); // 尝试获取DB连接2
                        System.out.println("任务2: 获取DB连接2");
                        Thread.sleep(1000);
                        dbConnections.release();
                    } catch (Exception e) { e.printStackTrace(); }
                });

                future2.get(); // 任务1阻塞等待任务2完成

                dbConnections.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            future1.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        threadPool.shutdown();
    }
}

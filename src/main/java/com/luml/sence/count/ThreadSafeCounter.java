package com.luml.sence.count;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luml
 * @description
 * 在并发环境下需要使用 incrementAndGet() 而不是普通的 i++：
 * 如果使用普通的 int 和 count++，最终结果通常会小于 10000，因为 ++ 操作不是原子的（包含读取、修改、写入三个步骤，中间可能被中断）。
 * 使用 incrementAndGet() 能保证最终结果严格等于 10000。
 * @date 2026/6/12
 */
public class ThreadSafeCounter {
    private static AtomicInteger count = new AtomicInteger(0);
    private static final int THREAD_COUNT = 10;
    private static final int INCREMENTS_PER_THREAD = 1000;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    // 原子自增，线程安全
                    count.incrementAndGet();
                }
            });
            threads[i].start();
        }

        // 等待所有线程执行完毕
        for (Thread thread : threads) {
            thread.join();
        }

        // 预期结果: 10 * 1000 = 10000
        System.out.println("Final Count: " + count.get());
    }
}

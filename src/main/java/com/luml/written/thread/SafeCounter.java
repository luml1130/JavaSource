package com.luml.written.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luml
 * @description
 * 线程安全的计数器
 *
 * ‌题目‌：实现一个线程安全的计数器，支持 increment 和 getCount。
 * ‌思路‌：可以使用 synchronized 关键字，或者更高效的 `AtomicInteger。
 *
 * @date 2026/6/8
 */
public class SafeCounter {

    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}

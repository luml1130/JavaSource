package com.luml.juc.tools.LockSupport;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author luml
 * @description
 * 经典应用场景1：实现自定义锁（FIFO Mutex）
 *      这是 JDK 官方文档中给出的经典示例，展示了如何利用 LockSupport 实现一个非重入的公平锁。
 * 解析：‌
 *     ‌公平性‌：通过 ConcurrentLinkedQueue 保证线程按请求顺序获取锁。
 *     ‌自旋+阻塞‌：while 循环配合 park。如果锁被占用，线程进入 WAITING 状态，避免 CPU 空转。
 *     ‌中断处理‌：park 不因中断抛出异常，因此需要手动记录中断状态并在退出前恢复，符合 Java 线程中断规范。
 * @date 2026/5/27
 */
public class FIFOMutex {
    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

    public void lock() {
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();

        // 1. 将当前线程加入等待队列
        waiters.add(current);

        // 2. 循环检查：只有当自己是队列第一个且锁空闲时，才能获取锁
        while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
            // 阻塞当前线程
            LockSupport.park(this);
            // 处理中断：park 被中断后会返回，但不清除中断状态
            if (Thread.interrupted()) {
                wasInterrupted = true;
            }
        }

        // 3. 获取锁成功，从队列移除
        waiters.remove();

        // 4. 恢复中断状态
        if (wasInterrupted) {
            current.interrupt();
        }
    }

    public void unlock() {
        // 1. 释放锁
        locked.set(false);
        // 2. 唤醒队列中的下一个线程（如果有）
        LockSupport.unpark(waiters.peek());
    }
}

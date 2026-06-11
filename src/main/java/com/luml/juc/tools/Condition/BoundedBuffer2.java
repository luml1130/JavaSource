package com.luml.juc.tools.Condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luml
 * @description
 * @date 2026/6/11
 */
public class BoundedBuffer2 {
    private final Lock lock = new ReentrantLock();
    // 创建两个条件：非满（供生产者等待）、非空（供消费者等待）
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity = 10;

    // 生产者方法
    public void produce(int item) throws InterruptedException {
        lock.lock();
        try {
            // 如果缓冲区满了，生产者在 notFull 条件上等待
            while (buffer.size() == capacity) {
                notFull.await();
            }
            buffer.add(item);
            System.out.println("Produced: " + item);
            // 生产完成后，通知消费者缓冲区非空
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // 消费者方法
    public int consume() throws InterruptedException {
        lock.lock();
        try {
            // 如果缓冲区空了，消费者在 notEmpty 条件上等待
            while (buffer.isEmpty()) {
                notEmpty.await();
            }
            int item = buffer.poll();
            System.out.println("Consumed: " + item);
            // 消费完成后，通知生产者缓冲区非满
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }
}

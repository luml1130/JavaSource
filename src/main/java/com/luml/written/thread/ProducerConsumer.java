package com.luml.written.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author luml
 * @description
 * 5. 实现生产者-消费者模型
 *
 * ‌题目‌：使用 wait() 和 notify() 实现一个简单的阻塞队列或生产消费逻辑。
 * ‌思路‌：当缓冲区满时生产者等待，空时消费者等待。
 *
 * @date 2026/6/8
 */
public class ProducerConsumer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity = 5;

    public void produce() throws InterruptedException {
        synchronized (this) {
            while (queue.size() == capacity) {
                wait(); // 队列满，等待
            }
            int val = (int) (Math.random() * 100);
            queue.add(val);
            System.out.println("Produced: " + val);
            notifyAll(); // 唤醒消费者
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                wait(); // 队列空，等待
            }
            int val = queue.poll();
            System.out.println("Consumed: " + val);
            notifyAll(); // 唤醒生产者
        }
    }
}

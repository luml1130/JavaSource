package com.luml.java.collection.queue.Deque;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author luml
 * @description
 * @date 2026/5/24
 */
public class LinkedBlockingDequeDemo {

    @Test
    public void test() throws InterruptedException {
        // 创建一个容量为 5 的双端阻塞队列
        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>(5);

        // 生产者线程
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String item = "Item-" + i;
                    // 如果队列满了，putFirst 会阻塞
                    deque.putFirst(item);
                    System.out.println("Produced (Head): " + item);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 消费者线程
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    // 如果队列空了，takeLast 会阻塞
                    String item = deque.takeLast();
                    System.out.println("Consumed (Tail): " + item);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        // 注意：实际应用中需要优雅地停止消费者线程
    }
}

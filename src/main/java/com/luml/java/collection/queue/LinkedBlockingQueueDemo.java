package com.luml.java.collection.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author luml
 * @description
 * @date 2026/5/23
 */
public class LinkedBlockingQueueDemo {

    //使用 LinkedBlockingQueue (最常用)
    public static void main(String[] args) {
        // 创建容量为 10 的阻塞队列
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

        // 生产者
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    queue.put("Item-" + i); // 队列满时阻塞
                    System.out.println("Produced: Item-" + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // 消费者
        new Thread(() -> {
            try {
                while (true) {
                    String item = queue.take(); // 队列空时阻塞
                    System.out.println("Consumed: " + item);
                    Thread.sleep(100); // 模拟消费耗时
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}

package com.luml.java.collection.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author luml
 * @description
 * @date 2026/5/23
 */
public class ConcurrentLinkedQueueDemo {

    /**
     * 使用 ConcurrentLinkedQueue (高并发非阻塞)
     * @param args
     */
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        // 生产者
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                queue.offer("Item-" + i); // 非阻塞，立即返回 true/false
            }
        }).start();

        // 消费者
        new Thread(() -> {
            while (true) {
                String item = queue.poll(); // 非阻塞，无元素返回 null
                if (item != null) {
                    System.out.println("Consumed: " + item);
                } else {
                    // 队列为空，可以做其他事或短暂休眠，避免 CPU 空转
                    break;
                }
            }
        }).start();
    }
}

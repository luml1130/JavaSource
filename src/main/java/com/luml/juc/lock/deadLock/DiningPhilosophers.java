package com.luml.juc.lock.deadLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luml
 * @description
 * 哲学家就餐问题（循环等待）
 * 这是一个著名的并发理论模型，模拟了多个线程循环等待资源导致的死锁。
 * ‌场景描述：‌
 *     5 位哲学家围坐圆桌，每人左右各有一根筷子（共 5 根）。
 *     哲学家思考时不需要筷子，进食时需要同时拿起左右两根筷子。
 *     ‌死锁条件‌：如果所有哲学家同时拿起左边的筷子，然后都去等待右边的筷子，由于右边的筷子都被邻居拿着，所有人都会无限等待。
 * @date 2026/7/10
 */
public class DiningPhilosophers {
    static class Chopstick {
        private final int id;
        public Chopstick(int id) { this.id = id; }
        public int getId() { return id; }
    }

    static class Philosopher implements Runnable {
        private final Chopstick left;
        private final Chopstick right;
        private final int id;

        public Philosopher(int id, Chopstick left, Chopstick right) {
            this.id = id;
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            try {
                // 模拟思考
                Thread.sleep((long) (Math.random() * 100));

                // 错误策略：先拿左边，再拿右边
                synchronized (left) {
                    System.out.println("哲学家 " + id + " 拿到了左筷子 " + left.getId());
                    Thread.sleep((long) (Math.random() * 100)); // 增加死锁概率
                    synchronized (right) {
                        System.out.println("哲学家 " + id + " 拿到了右筷子 " + right.getId());
                        System.out.println("哲学家 " + id + " 正在进食...");
                        Thread.sleep((long) (Math.random() * 100));
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        Chopstick[] chopsticks = new Chopstick[n];
        for (int i = 0; i < n; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        ExecutorService executor = Executors.newFixedThreadPool(n);
        for (int i = 0; i < n; i++) {
            // 左边是i，右边是(i+1)%n
            executor.execute(new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % n]));
        }
        executor.shutdown();
    }
}

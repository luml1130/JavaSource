package com.luml.juc.lock.deadLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luml
 * @description
 * 场景描述
 *     ‌线程 A‌：持有 Lock1，等待 Lock2。
 *     ‌线程 B‌：持有 Lock2，等待 Lock3。
 *     ‌线程 C‌：持有 Lock3，等待 Lock1。
 *     ‌结果‌：A->B->C->A 形成环形等待，所有线程永久阻塞。
 * @date 2026/7/10
 */
public class ThreeWayDeadlock {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();
    private static final ReentrantLock lock3 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // 线程1：持lock1，等lock2
        Thread t1 = new Thread(() -> {
            lock1.lock();
            try {
                System.out.println("线程1: 获取了 lock1");
                Thread.sleep(100); // 确保其他线程能获取到各自的锁
                System.out.println("线程1: 等待获取 lock2...");
                lock2.lock();
                try {
                    System.out.println("线程1: 获取了 lock2");
                } finally {
                    lock2.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
            }
        }, "Thread-1");

        // 线程2：持lock2，等lock3
        Thread t2 = new Thread(() -> {
            lock2.lock();
            try {
                System.out.println("线程2: 获取了 lock2");
                Thread.sleep(100);
                System.out.println("线程2: 等待获取 lock3...");
                lock3.lock();
                try {
                    System.out.println("线程2: 获取了 lock3");
                } finally {
                    lock3.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock2.unlock();
            }
        }, "Thread-2");

        // 线程3：持lock3，等lock1
        Thread t3 = new Thread(() -> {
            lock3.lock();
            try {
                System.out.println("线程3: 获取了 lock3");
                Thread.sleep(100);
                System.out.println("线程3: 等待获取 lock1...");
                lock1.lock();
                try {
                    System.out.println("线程3: 获取了 lock1");
                } finally {
                    lock1.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock3.unlock();
            }
        }, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        // 主线程等待，观察死锁
        t1.join();
        t2.join();
        t3.join();
    }
    /**
     * 线程1: 获取了 lock1
     * 线程2: 获取了 lock2
     * 线程3: 获取了 lock3
     * 线程1: 等待获取 lock2...
     * 线程2: 等待获取 lock3...
     * 线程3: 等待获取 lock1...
     */
}

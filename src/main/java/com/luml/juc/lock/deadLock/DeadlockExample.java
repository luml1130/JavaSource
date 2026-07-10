package com.luml.juc.lock.deadLock;

/**
 * @author luml
 * @description
 * @date 2026/7/10
 */
public class DeadlockExample {

    // 定义两个静态对象作为锁资源
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        // 线程1：先获取lock1，再尝试获取lock2
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " 获取了 lock1");
                try {
                    // 休眠以确保线程2有机会获取lock2，增加死锁复现概率
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 等待获取 lock2...");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " 获取了 lock2");
                }
            }
        }, "Thread-1");

        // 线程2：先获取lock2，再尝试获取lock1
        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " 获取了 lock2");
                try {
                    // 休眠以确保线程1有机会获取lock1
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 等待获取 lock1...");
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " 获取了 lock1");
                }
            }
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}

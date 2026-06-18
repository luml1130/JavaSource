package com.luml.juc.lock.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luml
 * @description
 * @date 2026/6/18
 */
public class ReentrantLockExample1 {

    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    /**
     * 基础用法：标准加锁解锁
     * 最典型的场景是保护共享资源（如计数器、库存）的线程安全。
     */
    public void increment() {
        // 1. 加锁
        lock.lock();
        try {
            // 2. 执行临界区代码
            count++;
            System.out.println("Current count: " + count);
        } finally {
            // 3. 务必在 finally 中释放锁，防止异常导致死锁
            lock.unlock();
        }
    }

    /**
     * 进阶用法：尝试获取与超时机制
     *
     * 用于避免无限期等待，提升系统的响应性和健壮性。
     */
    //2.1、非阻塞尝试 (tryLock)
    //如果锁不可用，立即返回 false，线程可以继续执行其他逻辑或稍后重试。
    public void tryIncrement() {
        // 尝试获取锁，不等待
        if (lock.tryLock()) {
            try {
                count++;
            } finally {
                lock.unlock();
            }
        } else {
            // 锁被占用，执行降级逻辑或记录日志
            System.out.println("Could not acquire lock, skipping operation.");
        }
    }
    //2.2超时等待 (`tryLock(long timeout, TimeUnit unit))
    //等待指定时间，若仍无法获取则放弃。这能有效防止死锁或长时间阻塞导致的线程堆积。
    public void timedIncrement() throws InterruptedException {
        // 等待最多 1 秒
        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                count++;
            } finally {
                lock.unlock();// 务必释放
            }
        } else {
            System.out.println("Timeout waiting for lock.");
        }
    }
   // or 写在try catch中
    public void safeOperation() {
        try {
            // 尝试获取锁，最多等待 1 秒
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    // 执行临界区代码
                } finally {
                    lock.unlock();
                }
            } else {
                // 获取锁失败，执行降级逻辑或重试，避免无限阻塞
                System.out.println("Could not acquire lock, avoiding deadlock.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    /**
     * 3、 高级特性：公平锁与条件变量
     * 3.1、公平锁 (Fair Lock)
     * 默认是非公平锁（吞吐量高）。若需保证“先来后到”，可构造公平锁，但性能会有所下降。
     * // true 表示公平锁
     *  private final ReentrantLock fairLock = new ReentrantLock(true);
     *
     */



}
/**
 * 3.2. 条件变量 (Condition)
 *
 * 替代 Object.wait()/notify()，支持更精细的线程通信（如多个等待队列）。
 */
class BoundedBuffer {
    final ReentrantLock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    //final Object[] items = new Object; //jdk8报错
    final Object[] items = new Object[2];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            // 如果缓冲区满，等待 notFull 信号
            while (count == items.length) {
                notFull.await();
            }
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            // 唤醒一个等待取数据的线程
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            // 如果缓冲区空，等待 notEmpty 信号
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            // 唤醒一个等待放数据的线程
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}

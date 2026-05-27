package com.luml.juc.tools.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luml
 * @description
 * @date 2026/5/27
 */
public class BoundedBuffer<E> {
    private final Lock lock = new ReentrantLock();
    // 创建两个独立的条件变量
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final Object[] items = new Object[2];//new Object;
    private int putptr, takeptr, count;

    // 生产者：放入元素
    public void put(E x) throws InterruptedException {
        lock.lock();
        try {
            // 如果缓冲区满了，生产者在 notFull 条件上等待
            while (count == items.length) {
                notFull.await();
            }
            // 执行放入操作
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;

            // 放入成功后，通知可能在等待“非空”条件的消费者
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // 消费者：取出元素
    @SuppressWarnings("unchecked")
    public E take() throws InterruptedException {
        lock.lock();
        try {
            // 如果缓冲区空了，消费者在 notEmpty 条件上等待
            while (count == 0) {
                notEmpty.await();
            }
            // 执行取出操作
            E x = (E) items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;

            // 取出成功后，通知可能在等待“非满”条件的生产者
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}

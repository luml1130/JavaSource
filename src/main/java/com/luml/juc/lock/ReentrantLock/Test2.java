package com.luml.juc.lock.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luml
 * @description
 * @date 2026/6/18
 */
public class Test2 implements Runnable{
    @Override
    public void run() {
        get();
    }

    ReentrantLock lock = new ReentrantLock();
    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }
    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }
    public static void main(String[] args) {
        Test2 ss = new Test2();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }
    /**
     * 11
     * 11
     * 12
     * 12
     * 13
     * 13
     */
}

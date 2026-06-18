package com.luml.juc.lock.ReentrantLock;

/**
 * @author luml
 * @description
 * @date 2026/6/18
 */
public class Test1 implements Runnable{
    @Override
    public void run() {
        get();
    }

    public synchronized void get(){
        System.out.println(Thread.currentThread().getId());
        set();
    }
    public synchronized void set(){
        System.out.println(Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        Test1 ss = new Test1();
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

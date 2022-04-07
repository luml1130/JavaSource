package com.luml.juc.lock.deadLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luml
 * @description:
 *  实现一个死锁:A线程获取B线程占有的锁，B线程获取A线程占有的锁
 * @date 2022/3/27
 */
public class MyDeadLock {
    //定义两把锁
    private static ReentrantLock lockA = new ReentrantLock();
    private static ReentrantLock lockB = new ReentrantLock();
    //测试
    public static void main(String[] args) {
        //启动线程A,B
        new Thread(new A()).start();
        new Thread(new B()).start();
    }

    static class A implements Runnable{
        @Override
        public void run() {
            Thread.currentThread().setName("A线程");
            //获取锁A
            lockA.lock();
            System.out.println(Thread.currentThread().getName()+"获取锁A");
            //模拟业务操作
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //再获取锁B
            lockB.lock();
            System.out.println(Thread.currentThread().getName()+"获取锁B");
            lockA.unlock();
            lockB.unlock();
        }
    }

    static class B implements Runnable{
        @Override
        public void run() {
            Thread.currentThread().setName("B线程");
            //获取锁B
            lockB.lock();
            System.out.println(Thread.currentThread().getName()+"获取锁B");
            //模拟业务操作
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //再获取锁A
            lockA.lock();
            System.out.println(Thread.currentThread().getName()+"获取锁A");
        }
    }
}

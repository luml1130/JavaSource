package com.luml.threadPool.ThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luml
 * @description
 * @date 2020/8/9
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        // 创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        // 创建线程
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();
        // 将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        // 关闭线程池
        pool.shutdown();
    }

    public static void main2(String[] args) {
        DeleteUserThread myRunnable = new DeleteUserThread();
        Thread th=new Thread(myRunnable);
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            newFixedThreadPool.execute(th);
        }
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行。。。");
    }
}

class DeleteUserThread implements Runnable{
    @Override
    public void run() {
        System.out.println("线程名字:" + Thread.currentThread().getName());
    }
}

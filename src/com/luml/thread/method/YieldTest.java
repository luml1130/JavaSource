package com.luml.thread.method;

/**
 * @author luml
 * @description:
 * 这就体现了，重新去和其他的线程竞争。
 *      如果只是单纯的让其他线程执行，那么就不会出现成对的输出语句。
 * @date 2021/4/3 6:27 下午
 */
public class YieldTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new com.luml.web.thread.method.tThread(), "thread1");
        Thread thread2 = new Thread(new com.luml.web.thread.method.tThread(), "thread2");
        Thread thread3 = new Thread(new com.luml.web.thread.method.tThread(), "thread3");
        Thread thread4 = new Thread(new com.luml.web.thread.method.tThread(), "thread4");
        Thread thread5 = new Thread(new com.luml.web.thread.method.tThread(), "thread5");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}

class tThread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+"执行结束");
    }
}

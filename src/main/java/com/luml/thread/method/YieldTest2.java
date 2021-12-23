package com.luml.thread.method;

/**
 * @author luml
 * @description:
 * 可以看到结果并非是优先级高的线程都排在前面。只是从结果来看，优先级高的线程先竞争到资源的可能性更大些。
 * @date 2021/4/3 6:29 下午
 */
public class YieldTest2 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new com.luml.thread.method.tThread2(), "thread1");
        Thread thread2 = new Thread(new com.luml.thread.method.tThread2(), "thread2");
        Thread thread3 = new Thread(new com.luml.thread.method.tThread2(), "thread3");
        Thread thread4 = new Thread(new com.luml.thread.method.tThread2(), "thread4");
        Thread thread5 = new Thread(new com.luml.thread.method.tThread2(), "thread5");
        thread1.setPriority(1);
        thread2.setPriority(3);
        thread3.setPriority(5);
        thread4.setPriority(7);
        thread5.setPriority(10);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}

class tThread2 implements Runnable{
    @Override
    public void run() {
        //System.out.println(Thread.currentThread().getName()+"开始执行");
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+"=="+i);
            Thread.yield();
        }
        //System.out.println(Thread.currentThread().getName()+"执行结束");
    }
}

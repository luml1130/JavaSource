package com.luml.thread.base;

/**
 * @author luml
 * @description: 线程不安全：共享数据：多个线程访问同一个变量。
 *   多个线程处理一个实例变量，只new了一个MyThread2线程实例，在创建5个Thread线程来调用MyThread2 这个线程实例
 *   （根据Java实例的特性，new的一个MyThread2实例，不同的Thread实例调用的是同一个MyThread2实例，
 *      对应的a、b、c、d、e五个的线程处理的都是MyThread2实例里的数据） 。
 * @date 2025/10/26
 */
public class ThreadSafeTest02 {
    public static void main(String[] args) {
        Thread2 thread2 = new Thread2();
        Thread a = new Thread(thread2);
        Thread b = new Thread(thread2);
        Thread c = new Thread(thread2);
        Thread d = new Thread(thread2);
        Thread e = new Thread(thread2);
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
class Thread2 extends Thread{
    private int count = 5;
    @Override
    public void run() {
        super.run();
        count--;
        //此处不用for循环，因为使用同步后其他线程就得不到运行的机会，会一直由一个线程进行减法运算
        System.out.println("由 "+ this.currentThread().getName() + " 计算，count=" +count);
    }
}
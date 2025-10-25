package com.luml.thread.base;

/**
 * @author luml
 * @description  测试线程和主线程执行完成顺序  说明主线程和myThread 两个线程并发执行哦
 * 输出：begin=false
 *      end=true
 *      run=true
 * @date 2025/10/25
 */
public class ThreadRandom {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        System.out.println("begin="+myThread.isAlive());
        myThread.start();
        System.out.println("end="+myThread.isAlive());

    }
}
class MyThread extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("run=" + this.isAlive());
    }
}
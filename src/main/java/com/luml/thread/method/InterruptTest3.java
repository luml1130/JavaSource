package com.luml.thread.method;

/**
 * @author luml
 * @description
 * @date 2021/4/3 7:14 下午
 */
public class InterruptTest3 {

    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(new MyRunnable());
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }

    /**
     * sleep begin!
     * sleep end!
     * 中断后正常退出
     */

    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    System.out.println("sleep begin!");
                    Thread.sleep(1000);
                    System.out.println("sleep end!");
                } catch (InterruptedException e) {
                    System.out.println("睡眠中遇中断进入catch，重置中断标志位，退出循环！");
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("中断后正常退出");
        }
    }
}

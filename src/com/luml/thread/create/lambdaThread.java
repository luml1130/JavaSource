package com.luml.thread.create;

/**
 * @author luml
 * @description
 * @date 2021/11/11
 */
public class lambdaThread {

    public static void main(String[] args) {
        new Thread(() -> {
            for (int b = 0; b < 5; b++) {
                System.out.println(Thread.currentThread().getName() + "--" + b);
            }
        },"savePushMessageThread").start();
        System.out.println(Thread.currentThread().getName()+"主线程结束");
    }
}

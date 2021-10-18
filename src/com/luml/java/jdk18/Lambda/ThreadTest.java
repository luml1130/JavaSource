package com.luml.java.jdk18.Lambda;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author luml
 * @description
 * @date 2020/4/23 10:01
 */
public class ThreadTest {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":使用正常方法创建线程");
            }
        }).start();

       /* Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2222");
            }
        },"Thread-luml");
        System.out.println("ThreadName="+t.getName());
        t.start();*/

        new Thread(()-> {
            System.out.println(Thread.currentThread().getName()+":使用lambda表达式创建线程");
        }).start();
    }
}

package com.luml.thread.method;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author luml
 * @description
 *  使用ExecutorService线程池异步执行
 *  执行无返回结果时用execute方法
 * @date 2021/6/26 09:19
 */
public class ExecutorServiceTest {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(50);
//      ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("3秒等待开始");
                    Thread.sleep(3 * 1000);
                    System.out.println("等待结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        int  QueueSize = ((ThreadPoolExecutor)cachedThreadPool).getQueue().size();
        System.out.println("---------队列长度QueueSize=" + QueueSize);

        System.out.println("--------------->");
    }

    // jdk 1.8写法
    public static void main1(String[] args) {
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(50);
        cachedThreadPool.execute(() -> proexect());
        System.out.println("--------------->");
    }

    public static void proexect() {
        try {
            System.out.println("3秒等待开始");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1 * 1000);
                System.out.println(i);
            }
            System.out.println("等待结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

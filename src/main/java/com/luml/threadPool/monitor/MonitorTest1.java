package com.luml.threadPool.monitor;

import java.util.concurrent.TimeUnit;

/**
 * @author luml
 * @description
 * @date 2021/4/4 10:28 上午
 */
public class MonitorTest1 {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread threads[] = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(task);
            threads[i].setPriority(i + 1);
            threads[i].start();
        }
        /**
         * 创建重复10次操作的循环，每次操作输出之前加载的线程信息到控制台，在内部创建重复五次操作的循环：
         */
        for (int j = 0; j < 10; j++) {
            System.out.printf("Main: Logging threads\n");
            for (int i = 0; i < threads.length; i++) {
                //输出每个线程的名称、状态、组别和堆栈跟踪的长度到控制台：
                System.out.printf("**********************\n");
                System.out.printf("Main: %d: Id: %d Name: %s: Priority: %d\n",i, threads[i].getId(),threads[i].getName(), threads[i].getPriority());
                System.out.printf("Main: Status: %s\n",threads[i].getState());
                System.out.printf("Main: Thread Group: %s\n", threads[i].getThreadGroup());
                System.out.printf("Main: Stack Trace: \n");
                //输出线程堆栈跟踪的循环到控制台：
                for (int t=0; t<threads[i].getStackTrace().length; t++) {
                    System.out.printf("Main: %s\n",threads[i].getStackTrace()[t]);
                }
                System.out.printf("**********************\n");
            }
            TimeUnit.SECONDS.sleep(1);
        }

    }
}

class Task implements Runnable {
    @Override
    public void run() {
        for (int i=0; i<100; i++) {
            try {
                //休眠100毫秒
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.printf("%s: %d\n",Thread.currentThread().getName(),i);
        }
    }
}

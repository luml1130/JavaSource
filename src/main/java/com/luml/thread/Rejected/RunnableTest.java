package com.luml.thread.Rejected;

/**
 * @author luml
 * @description
 * @date 2021/6/26 07:32
 */
public class RunnableTest implements Runnable{
    volatile int i;

    public RunnableTest(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("start");
        System.out.println("task_" +i + " is running");
        System.out.println("end");
    }
}

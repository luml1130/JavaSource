package com.luml.juc.lock.LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;

/**
 * @author luml
 * @description:
 * https://mp.weixin.qq.com/s/xSro-bwg__ir9EXwoCJ-rg
 * 一道阿里经典的多线程协同工作面试题
 * 有3个独立的线程，一个只会输出A，一个只会输出B，一个只会输出C，
 * 在三个线程启动的情况下，请用合理的方式让他们按顺序打印ABCABC。
 *
 * 思路如下
 *     准备3个线程，分别固定打印A、B、C
 *     线程输出完A、B、C后需要阻塞等待唤醒
 *     额外准备第4个线程，作为另外3个线程的调度器，有序的控制3个线程执行
 * @date 2021/8/8 下午10:21
 */
public class LockSupportMain {

    public static void main(String[] args) {

        LockSupportMain lockSupportMain = new LockSupportMain();

        //定义线程t1、t2、t3执行的函数方法
        Consumer<String> consumer = str -> {
            while (true) {
                //线程消费许可证，并传入blocker，方便后续排查问题
                LockSupport.park(lockSupportMain);
                //防止线程是因中断操作唤醒
                if (Thread.currentThread().isInterrupted()){
                    throw new RuntimeException("线程被中断，异常结束");
                }
                System.out.println(Thread.currentThread().getName() + ":" + str);
            }
        };

        /**
         * 定义分别输出A、B、C的线程
         */
        Thread t1 = new Thread(() -> {
            consumer.accept("A");
        },"T1");
        Thread t2 = new Thread(() -> {
            consumer.accept("B");
        },"T2");
        Thread t3 = new Thread(() -> {
            consumer.accept("C");
        },"T3");

        /**
         * 定义调度线程
         */
        Thread dispatch = new Thread(() -> {
            int i=0;
            try {
                while (true) {
                    if((i%3)==0) {
                        //线程t1设置许可证，并唤醒线程t1
                        LockSupport.unpark(t1);
                    }else if((i%3)==1) {
                        //线程t2设置许可证，并唤醒线程t2
                        LockSupport.unpark(t2);
                    }else {
                        //线程t3设置许可证，并唤醒线程t3
                        LockSupport.unpark(t3);
                    }
                    i++;
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //启动相关线程
        t1.start();
        t2.start();
        t3.start();
        dispatch.start();

    }
    /**
     * T1:A
     * T2:B
     * T3:C
     * T1:A
     * T2:B
     * T3:C
     * T1:A
     * T2:B
     * T3:C
     * T1:A
     */
}

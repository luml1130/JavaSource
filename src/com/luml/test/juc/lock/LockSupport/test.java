package com.luml.test.juc.lock.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author luml
 * @description:
 * 子线程th调用LockSupport.park()阻塞，
 * 主线程睡眠2秒后，执行LockSupport.unpark(th)唤醒th线程，先阻塞后唤醒非常好理解，
 * @date 2021/8/8 下午10:11
 */
public class test {
    public static void main(String[] agrs) throws InterruptedException {
        Thread th = new Thread(() -> {
            //阻塞当前线程
            LockSupport.park();
            System.out.println("子线程执行---------");
        });
        th.start();
        //睡眠2秒
        Thread.sleep(2000);
        System.out.println("主线程执行---------");
        //唤醒线程
        LockSupport.unpark(th);
    }
    /**
     * 输出结果：
     * 主线程执行---------
     * 子线程执行---------
     */

}

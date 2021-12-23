package com.luml.juc.lock.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author luml
 * @description:
 * 先唤醒th线程，再阻塞th线程，最终th线程没有被阻塞，这是为什么？
 * 下面LockSupport的设计思路会为读者们解开疑惑，并更进一步明确是park和unpark的语义（从广义上来说park和unpark代表阻塞和唤醒）。
 * @date 2021/8/8 下午10:13
 */
public class test2 {

    public static void main(String[] args) throws InterruptedException {
        Thread th = new Thread(() -> {
            //唤醒当前线程
            LockSupport.unpark(Thread.currentThread());
            //阻塞当前线程
            LockSupport.park();
            System.out.println("子线程执行---------");
        });
        th.start();
        //睡眠2秒
        Thread.sleep(2000);
        System.out.println("主线程执行---------");
    }
    /**
     *  输出结果：
     * 子线程执行---------
     * 主线程执行---------
     */
}

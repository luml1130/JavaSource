package com.luml.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luml
 * @description 测试Lock 接口的Interruptibly()方法和lock()方法区别
 * @date 2020/4/17 9:57
 */
public class LockInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    /**
                     * 如果换成lock方法会提示try catch 无效，这是因为lock会忽略interrupt()引发的异常不会抛出异常；
                     */
                    //lock.lock();
                } catch (InterruptedException e) {
                    System.out.println("interrupt");
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t1.interrupt();
        //sleep方法必须抛出InterruptedException
        Thread.sleep(1);
    }
    /**
     * 程序会打印interrupt
     * 然后报错信息
     */
}

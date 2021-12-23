package com.luml.juc.lock.customer;

/**
 * @author luml
 * @description:
 * 使用自定义的独占锁来同步两个线程对j++。
 * 无论执行多少次输出内容都是：200000
 * @date 2021/7/25 下午9:50
 */
public class NonReentrantLockTest {

    private static int j = 0;

    public static void main(String[] args) throws InterruptedException {
        NonReentrantLock  nonReentrantLock = new NonReentrantLock();

        Runnable runnable = () -> {
            //获取锁
            nonReentrantLock.lock();
            for (int i = 0; i < 100000; i++) {
                j++;
            }
            //释放锁
            nonReentrantLock.unlock();
        };

        Thread thread = new Thread(runnable);
        Thread threadTwo = new Thread(runnable);

        thread.start();
        threadTwo.start();

        thread.join();
        threadTwo.join();

        System.out.println(j);

    }
}

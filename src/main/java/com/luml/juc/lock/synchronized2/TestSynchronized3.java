package com.luml.juc.lock.synchronized2;

/**
 * @author luml
 * @description synchronized同时修饰静态和非静态方法
 * 结果：
 * .test1 : 4
 * .test2 : 4
 * .test1 : 3
 * .test2 : 3
 * .test2 : 2
 * .test1 : 2
 * .test2 : 1
 * .test1 : 1
 * .test1 : 0
 * .test2 : 0
 * 上面代码synchronized同时修饰静态方法和实例方法，但是运行结果是交替进行的，
 * 这证明了类锁和对象锁是两个不一样的锁，控制着不同的区域，它们是互不干扰的。
 * 同样，线程获得对象锁的同时，也可以获得该类锁，即同时获得两个锁，这是允许的。
 * @date 2020/11/27
 */
public class TestSynchronized3 {
    public synchronized void test1() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
        }
    }

    public static synchronized void test2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
        }
    }

    public static void main(String[] args) {
        final TestSynchronized3 myt2 = new TestSynchronized3();
        Thread test1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myt2.test1();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable() {
            @Override
            public void run() {
                TestSynchronized3.test2();
            }
        }, "test2");
        test1.start();
        test2.start();
        //         TestRunnable tr=new TestRunnable();
        //         Thread test3=new Thread(tr);
        //         test3.start();
    }
}

package com.luml.test.juc.lock.synchronized2;

/**
 * @author luml
 * @description 类锁的修饰（静态）方法和代码块
 * 结果：
 * .test1 : 4
 * .test1 : 3
 * .test1 : 2
 * .test1 : 1
 * .test1 : 0
 * .test2 : 4
 * .test2 : 3
 * .test2 : 2
 * .test2 : 1
 * .test2 : 0
 * 类锁修饰方法和代码块的效果和对象锁是一样的，
 * 因为类锁只是一个抽象出来的概念，只是为了区别静态方法的特点，因为静态方法是所有对象实例共用的，
 * 所以对应着synchronized修饰的静态方法的锁也是唯一的，所以抽象出来个类锁。
 * 其实这里的重点在下面这块代码，synchronized同时修饰静态和非静态方法
 * @date 2020/11/27
 */
public class TestSynchronized2 {

    public void test1() {
        synchronized (TestSynchronized.class) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
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
        final TestSynchronized2 myt2 = new TestSynchronized2();
        Thread test1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myt2.test1();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable() {
            @Override
            public void run() {
                TestSynchronized2.test2();
            }
        }, "test2");
        test1.start();
        test2.start();
        //         TestRunnable tr=new TestRunnable();
        //         Thread test3=new Thread(tr);
        //         test3.start();
    }
}

package com.luml.test.juc.lock.synchronized2;

/**
 * @author luml
 * @description
 * 上述的代码，
 * 第一个方法使用了同步代码块的方式进行同步，传入的对象实例是this，表明是当前对象，
 *          当然，如果需要同步其他对象实例，也不可传入其他对象的实例；
 *          都是对象锁；
 * 第二个方法是修饰方法的方式进行同步。因为第一个同步代码块传入的this，
 *       所以两个同步代码所需要获得的对象锁都是同一个对象锁，
 *  下面main方法时分别开启两个线程，分别调用test1和test2方法，
 *       那么两个线程都需要获得该对象锁，另一个线程必须等待。
 *       上面也给出了运行的结果可以看到：
 *       直到test2线程执行完毕，释放掉锁，test1线程才开始执行。
 *       （可能这个结果有人会有疑问，代码里面明明是先开启test1线程，
 *          为什么先执行的是test2呢？这是因为java编译器在编译成字节码的时候，
 *          会对代码进行一个重排序，也就是说，编译器会根据实际情况对代码进行一个合理的排序，
 *          编译前代码写在前面，在编译后的字节码不一定排在前面，所以这种运行结果是正常的，
 *           这里是题外话，最主要是检验synchronized的用法的正确性）
 *  运行结果：
 *  1. test2 : 4
 * 2. test2 : 3
 * 3. test2 : 2
 * 4. test2 : 1
 * 5. test2 : 0
 * 6. test1 : 4
 * 7. test1 : 3
 * 8. test1 : 2
 * 9. test1 : 1
 * 10. test1 : 0
 * 如果我们把test2方法的synchronized关键字去掉，执行结果会如何呢？
 * 我们可以看到，结果输出是交替着进行输出的，这是因为，
 * 某个线程得到了对象锁，
 * 但是另一个线程还是可以访问没有进行同步的方法或者代码。
 * 进行了同步的方法（加锁方法）和没有进行同步的方法（普通方法）是互不影响的，
 * 一个线程进入了同步方法，得到了对象锁，
 * 其他线程还是可以访问那些没有同步的方法（普通方法）。
 * 这里涉及到内置锁的一个概念（此概念出自java并发编程实战第二章）：
 *          对象的内置锁和对象的状态之间是没有内在的关联的，
 *          虽然大多数类都将内置锁用做一种有效的加锁机制，
 *          但对象的域并不一定通过内置锁来保护。
 *          当获取到与对象关联的内置锁时，并不能阻止其他线程访问该对象，
 *          当某个线程获得对象的锁之后，只能阻止其他线程获得同一个锁。
 *          之所以每个对象都有一个内置锁，是为了免去显式地创建锁对象
 * @date 2020/11/26
 */
public class TestSynchronized {
    public void test1() {
        synchronized (this) {
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

    public synchronized void test2() {
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
        final TestSynchronized myt2 = new TestSynchronized();
        Thread test1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myt2.test1();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable() {
            @Override
            public void run() {
                myt2.test2();
            }
        }, "test2");
        test1.start();
        test2.start();
//         TestRunnable tr=new TestRunnable();
//         Thread test3=new Thread(tr);
//         test3.start();
    }

}

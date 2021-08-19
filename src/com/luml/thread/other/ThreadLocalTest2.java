package com.luml.thread.other;

/**
 * @author luml
 * @description:
 *  从运行的结果我们可以看到threadLocal1进行set值对threadLocal2并没有任何影响！
 * @date 2020/4/23 9:55
 */
public class ThreadLocalTest2 {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    threadLocal.set(i);
                    System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                threadLocal.remove();
            }
        }, "threadLocal1").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                threadLocal.remove();
            }
        }, "threadLocal2").start();
    }
}
/**
 * threadLocal1====0
 * threadLocal2====null
 * threadLocal2====null
 * threadLocal1====1
 * threadLocal1====2
 * threadLocal2====null
 * threadLocal2====null
 * ....
 */

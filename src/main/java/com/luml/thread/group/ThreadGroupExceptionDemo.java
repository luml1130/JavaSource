package com.luml.thread.group;

/**
 * @author luml
 * @description
 * @date 2026/5/21
 */
public class ThreadGroupExceptionDemo {

    /**
     * 当一个线程抛出异常时，同组其他线程不受影响：
     * 运行结果预期‌：
     *     t1 抛出 ArithmeticException 并立即终止。
     *     t2 不受影响，继续打印直到循环结束。
     * @param args
     */
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("测试组");

        // 线程1：故意制造异常
        Thread t1 = new Thread(group, () -> {
            System.out.println("线程1开始运行...");
            // 制造除零异常
            int i = 1 / 0;
            System.out.println("线程1结束"); // 这行不会执行
        }, "异常线程");

        // 线程2：正常运行
        Thread t2 = new Thread(group, () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("线程2正在运行: " + i);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        }, "正常线程");

        t1.start();
        t2.start();
    }
}

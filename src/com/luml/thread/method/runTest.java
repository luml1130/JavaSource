package com.luml.thread.method;

/**
 * @author luml
 * @description:
 * start()用来启动一个线程，当调用start方法后，系统才会开启一个新的线程，进而调用run()方法来执行任务，
 * 而单独的调用run()就跟调用普通方法是一样的，已经失去线程的特性了。因此在启动一个线程的时候一定要使用start()而不是run()。
 * @date 2021/3/29 11:17 下午
 */
public class runTest {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                pong();
            }
        };
        t.run();
        System.out.print("ping");
        /**
         * 输出B：pongping
         */
    }
    static void pong() {
        System.out.print("pong");
    }
}


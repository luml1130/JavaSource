package com.luml.juc.tools.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author luml
 * @description
 * 一个简单示例。Decrementer 三次调用 countDown() 之后，等待中的 Waiter 才会从 await() 调用中释放出来。
 * @date 2021/7/22 下午9:29
 */
public class test {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        Waiter      waiter      = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);
        new Thread(waiter)     .start();
        new Thread(decrementer).start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Waiter implements Runnable{
        CountDownLatch latch = null;
        public Waiter(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public  void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Waiter Released");
        }
    }

    public static class Decrementer implements Runnable {
        CountDownLatch latch = null;
        public Decrementer(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                this.latch.countDown();
                Thread.sleep(1000);
                this.latch.countDown();
                Thread.sleep(1000);
                this.latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



package com.luml.thread.method;

import java.util.concurrent.TimeUnit;

/**
 * @author luml
 * @description
 * 除了中断之外 还可以用一个boolean变量;
 * 这种中断方式能在终止线程时候清理资源 而不是武断地终止线程，安全优雅
 * @date 2021/4/3 7:27 下午
 */
public class InterruptTest4FromBook {
    public static void main(String[] args) throws InterruptedException {

        Runner one = new Runner();
        Thread countThread = new Thread(one,"CountThread");
        countThread.start();
        //睡眠1秒，main线程对CountThread进行中断，使CountThread能感知中断而结束
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        /*Runner two = new Runner();
        countThread  = new Thread(two,"CountThread");
        countThread.start();
        //睡眠1秒，main线程对Runner two 进行取消。使CountThread能感知on = false 而结束
        TimeUnit.SECONDS.sleep(1);
        two.cancel();*/

    }
    private static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (/**on && **/!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i = " + i);
        }
        public void cancel(){
            on = false;
        }
    }
}

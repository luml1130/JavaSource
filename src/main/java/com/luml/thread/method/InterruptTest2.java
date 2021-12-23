package com.luml.thread.method;

/**
 * @author luml
 * @description
 * @date 2021/4/3 7:04 下午
 */
public class InterruptTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(new MyRunnable());
        t.start();
        Thread.sleep(5);
        t.interrupt();
        //等待指定的线程终止
        t.join();
        System.out.println("end");
    }

    /**
     * i=151
     * i=152
     * i=153
     * i=154
     * i=155
     * i=156
     * end
     *  这里完成了一个简单的中断处理程序。但令人困惑的还不止这两个方法，我们在继 续深入下去，最后给出Java中断线程的最佳实践和模板代码。
     */
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            for(int i=0;!Thread.currentThread().isInterrupted()&&i<50000;i++)
            {
                System.out.println("i="+(i+1));
            }
        }

    }
}

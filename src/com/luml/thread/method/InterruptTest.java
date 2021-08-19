package com.luml.thread.method;

/**
 * @author luml
 * @description
 * 结论： 单纯用 interrupt()中断线程方法并不能停止当前正在运行的线程，需要配合其它方法才能正确停止线程。
 * @date 2021/4/3 7:02 下午
 */
public class InterruptTest {

    public static void main(String[] args) {

        Thread t=new Thread(new MyRunnable());
        t.start();
        t.interrupt();
    }

    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<50000;i++){
                System.out.println("i="+(i+1));
            }
        }
    }
}

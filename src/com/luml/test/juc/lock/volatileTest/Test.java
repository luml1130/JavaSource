package com.luml.test.juc.lock.volatileTest;

/**
 * @author luml
 * @description:
 * 测试：volatile保证原子性吗
 * @date 2020/5/16 1:23
 */
public class Test {
    public volatile int inc = 0;
    public void increase() {
        inc++;
    }
    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    for(int j=0;j<1000;j++) {
                        test.increase();
                    }
                };
            }.start();
        }
        while(Thread.activeCount()>1){  //保证前面的线程都执行完
            Thread.yield();
        }
        System.out.println(test.inc);
    }
}

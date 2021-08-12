package com.luml.thread.other;

import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2021/8/12 下午11:49
 */
public class threadNameTest {

    @Test
    public  void setName1(){


        new Thread("线程1") {
            @Override
            public void run() {
                System.out.println(this.getName() +": aaaaaa");
            }
        }.start();

        new Thread("线程2") {
            @Override
            public void run() {
                System.out.println(this.getName() +": bbbbb");
            }
        }.start();
    }

    @Test
    public void setName2(){
        new Thread() {
            @Override
            public void run() {
                this.setName("线程A");
                System.out.println(this.getName() +": aaaaaa");
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                this.setName("线程B");
                System.out.println(this.getName() +": bbbbb");
            }
        }.start();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                //获取当前线程对象
                System.out.println(Thread.currentThread().getName()); //xwork-
                //System.out.println("我是" + this.getName());//我是xwork-
            }
        };
        t2.setName("xwork-");
        t2.start();
        //System.out.println(t2.getName()); //xwork-
        //主线程名称
        System.out.println(Thread.currentThread().getName());
    }


}

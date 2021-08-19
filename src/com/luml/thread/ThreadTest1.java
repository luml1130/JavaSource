package com.luml.thread;

/**
 * @author luml
 * @description:
 * 设计4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少1。写出程序。
 * 以下程序使用内部类实现线程，对j增减的时候没有考虑顺序问题。
 * @date 2021/3/29 11:20 下午
 */
public class ThreadTest1 {
    private int j;
    public static void main(String[] args) {
        ThreadTest1 tt= new ThreadTest1();
        Inc inc=tt.new Inc();
        Dec dec=tt.new Dec();
        for(int i=0;i<2;i++){
            Thread t=new Thread(inc);
            t.start();
            t=new Thread(dec);
            t.start();
        }
    }

    class Dec implements Runnable{
        @Override
        public void run(){
            for(int i=0;i<100;i++){
                dec();
            }
        }
    }
    private synchronized void dec(){
        j--;
        System.out.println(Thread.currentThread().getName()+"-dec:"+j);
    }
    class Inc implements Runnable{
        @Override
        public void run(){
            for(int i=0;i<100;i++){
                inc();
            }
        }
    }
    private synchronized void inc(){
        j++;
        System.out.println(Thread.currentThread().getName()+"-inc:"+j);
    }
}


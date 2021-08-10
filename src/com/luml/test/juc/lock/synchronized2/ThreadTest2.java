package com.luml.test.juc.lock.synchronized2;

/**
 * @author luml
 * @description
 * 例子程序ThreadTest2所达到的效果和例子ThreadTest的效果一样，都是使得两个线程的执行顺序进行，而不是并发进行，
 * 当一个线程执行时，将object对象锁住，另一个线程就不能执行对应的块。
 * @date 2020/4/21 17:24
 */
public class ThreadTest2 {
    public static void main(String[] args){
        Example example = new Example();
        Thread t1 = new Thread1(example);
        Thread t2 = new Thread2(example);
        t1.start();
        t2.start();
    }
}

class Example{
    private Object object = new Object();
    public void execute(){
        synchronized (object){
            for (int i = 0; i < 20; ++i){
                try{
                    Thread.sleep((long) Math.random() * 1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("Hello: " + i);
            }
        }
    }
    public void execute2(){
        synchronized (object){
            for (int i = 0; i < 20; ++i){
                try{
                    Thread.sleep((long) Math.random() * 1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("World: " + i);
            }
        }
    }
}

class Thread1 extends Thread{
    private Example example;
    public Thread1(Example example){
        this.example = example;
    }
    @Override
    public void run(){
        example.execute();
    }
}

class Thread2 extends Thread{
    private Example example;
    public Thread2(Example example){
        this.example = example;
    }
    @Override
    public void run(){
        example.execute2();
    }
}

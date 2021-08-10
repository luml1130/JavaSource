package com.luml.test.juc.lock.synchronized2;

/**
 * @author luml
 * @description
 * 是否在execute()方法前加上synchronized关键字，这个例子程序的执行结果会有很大的不同。
 * 如果不加synchronized关键字，则两个线程同时执行execute()方法，输出是两组并发的。
 * 如果加上synchronized关键字，则会先输出一组0到9，然后再输出下一组，说明两个线程是顺次执行的。
 * @date 2020/4/21 17:21
 */
public class ThreadTest {
    public static void main(String[] args){
        Example333 example333 = new Example333();
        Thread t1 = new Thread333(example333);
        Thread t2 = new Thread333(example333);
        t1.start();
        t2.start();
    }
}

class Example333{
    public synchronized void execute(){
        for (int i = 0; i < 10; ++i){
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Hello: " + i);
        }
    }
}

class Thread333 extends Thread{
    private Example333 example333;
    public Thread333(Example333 example333)    {
        this.example333 = example333;
    }
    @Override
    public void run(){
        example333.execute();
    }
}
/**
 * 输出结果
 * Hello: 0
 * Hello: 1
 * Hello: 2
 * Hello: 3
 * Hello: 4
 * Hello: 5
 * Hello: 6
 * Hello: 7
 * Hello: 8
 * Hello: 9
 * Hello: 0
 * Hello: 1
 * Hello: 2
 * Hello: 3
 * Hello: 4
 * Hello: 5
 * Hello: 6
 * Hello: 7
 * Hello: 8
 * Hello: 9
 */

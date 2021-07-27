package com.luml.test.juc.Collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author luml
 * @description:
 *  ArrayBlockingQueue的集合容量为1，因此3个生产者线程无法连续加入元素，
 *  必须等消费者取出一个元素之后，3个生产者才能加入一个元素
 * @date 2020/11/25
 */
public class ArrayBlockingQueueTest {
    public static void main(String[] args){
        //创建一个容量为1的ArrayBlockingQueue
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);
        //创建3个生产者线程
        new Producer2(bq).start();
        new Producer2(bq).start();
        new Producer2(bq).start();
        //启动一个消费者线程
        new Consumer2(bq).start();
    }
}

class Producer2 extends Thread{
    private BlockingQueue<String> bq;
    public Producer2(BlockingQueue<String> bq){
        this.bq = bq;
    }
    @Override
    public void run(){
        String[] Arr = new String[]{"Java","C","Python"};
        for (int i =0;i<9999;i++){
            System.out.println(getName()+"生产者准备生产元素");
            try{
                Thread.sleep(200);
                //尝试放入元素，如果队列已满，则线程被阻塞
                bq.put(Arr[i%3]);
            } catch(Exception ex){ex.printStackTrace();}
            System.out.println(getName()+"生产完成："+bq);
        }
    }
}

class Consumer2 extends Thread{
    private BlockingQueue<String> bq;
    public Consumer2(BlockingQueue<String> bq){
        this.bq=bq;
    }
    @Override
    public void run(){
        while(true){
            System.out.println(getName()+"消费者准备消费元素");
            try{
                Thread.sleep(200);
                //尝试去除元素，如果队列已空，则线程被阻塞
                bq.take();
            }catch (Exception ex){ex.printStackTrace();}
            System.out.println(getName()+"消费完成："+bq);
        }
    }
}

package com.luml.test.juc.Collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author luml
 * @description：
 * BlockingQueueExample 类分别在两个独立的线程中启动了一个 Producer 和 一个 Consumer。
 *      Producer 向一个共享的 BlockingQueue 中注入字符串，
 *      而 Consumer 则会从中把它们拿出来。
 * @date 2020/11/25
 */
public class ArrayBlockingQueueExample {

    public static void main(String[] args) throws Exception {
        BlockingQueue queue = new ArrayBlockingQueue(1024);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
        Thread.sleep(4000);
    }

}

class Producer implements Runnable{
    protected BlockingQueue queue = null;
    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            queue.put("1");
            Thread.sleep(1000);
            queue.put("2");
            Thread.sleep(1000);
            queue.put("3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 注意它在每次 put() 调用时是如何休眠一秒钟的。这将导致 Consumer 在等待队列中对象的时候发生阻塞。
 */
class Consumer implements Runnable{
    protected BlockingQueue queue = null;
    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }
    @Override
    public  void run() {
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

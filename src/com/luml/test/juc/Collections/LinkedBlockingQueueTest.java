package com.luml.test.juc.Collections;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luml
 * @description
 * @date 2020/11/25
 */
public class LinkedBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        // 声明一个容量为10的缓存队列
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
        //new了三个生产者和一个消费者
        ProducerA producer1 = new ProducerA(queue);
        ProducerA producer2 = new ProducerA(queue);
        ProducerA producer3 = new ProducerA(queue);
        ConsumerA Consumer = new ConsumerA(queue);
        // 借助Executors
        ExecutorService service = Executors.newCachedThreadPool();
        // 启动线程
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(Consumer);
        // 执行10s
        Thread.sleep(10 * 1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();
        Thread.sleep(2000);
        // 退出Executor
        service.shutdown();
    }
}

class ProducerA implements Runnable {
    //是否在运行标志
    private volatile boolean  isRunning = true;
    private BlockingQueue queue;
    //自动更新的值
    private static AtomicInteger count = new AtomicInteger();
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;
    //构造函数
    public ProducerA(BlockingQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        String data = null;
        Random r = new Random();
        System.out.println("启动生产者线程！");
        try{
            while (isRunning) {
                System.out.println("正在生产数据...");
                //取0~DEFAULT_RANGE_FOR_SLEEP值的一个随机数
                Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                //以原子方式将count当前值加1
                data = "data:" + count.incrementAndGet();
                System.out.println("将数据：" + data + "放入队列...");
                //设定的等待时间为2s，如果超过2s还没加进去返回true
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("放入数据失败：" + data);
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出生产者线程！");
        }
    }
    public void stop() {
        isRunning = false;
    }
}

class ConsumerA implements Runnable {
    private BlockingQueue<String> queue;
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;
    //构造函数
    public ConsumerA(BlockingQueue<String> queue) {
        this.queue = queue;
     }
    public void run() {
        System.out.println("启动消费者线程！");
        Random r = new Random();
        boolean isRunning = true;
        try {
            while (isRunning) {
                System.out.println("正从队列获取数据...");
                //有数据时直接从队列的队首取走，无数据时阻塞，在2s内有数据，取走，超过2s还没数据，返回失败
                String data = queue.poll(2, TimeUnit.SECONDS);
                if (null != data) {
                    System.out.println("拿到数据：" + data);
                    System.out.println("正在消费数据：" + data);
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                } else {
                    // 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
                    isRunning = false;
                }
            }
         } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
         } finally {
            System.out.println("退出消费者线程！");
         }
     }
}

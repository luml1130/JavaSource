package com.luml.thread.Rejected;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author luml
 * @description:
 * 线程池pool的”最大池大小”和”核心池大小”都为1(THREDSSIZE)，这意味着”线程池能同时运行的任务数量最大只能是1”。
 * 线程池pool的阻塞队列是[ArrayBlockingQueue]，ArrayBlockingQueue是一个有界的阻塞队列，ArrayBlockingQueue的容量为1。
 *              这也意味着线程池的阻塞队列只能有一个线程池阻塞等待。 　　
 * 根据”“中分析的execute()代码可知：线程池中共运行了2个任务。第1个任务直接放到Worker中，通过线程去执行；
 *              第2个任务放到阻塞队列中等待。其他的任务都被丢弃了！
 * 原文链接：https://blog.csdn.net/qq_41650000/article/details/105239934
 * @date 2021/6/26 07:33
 */
public class DiscardPolicyDemo {

    /**
     * 核心线程数
     */
    private static final int THREDSSIZE = 1;

    /**
     * 最大线程数
     */
    private static final int CAPACITY = 1;

    public static void main(String[] args) {
        //创建线程池  策略为丢弃
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(THREDSSIZE,THREDSSIZE,0,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(CAPACITY),new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 10; i++){
            RunnableTest runnableTest = new RunnableTest(i);
            threadPoolExecutor.execute(runnableTest);
        }
        threadPoolExecutor.shutdown();
    }
    /**
     * start
     * task_0 is running
     * end
     * start
     * task_1 is running
     * end
     */
}

package com.luml.thread.Rejected;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author luml
 * @description:
 *  当有任务添加到线程池被拒绝时，线程池会丢弃阻塞队列中末尾的任务，然后将被拒绝的任务添加到末尾。
 * @date 2021/6/26 07:39
 */
public class DiscardOldestPolicyDemo {
    private static final int THREAD_SIZE = 1;

    private static final int CAPACITY = 1;

    public static void main(String[] args) {

        long keepAliveTime = 1;

        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(CAPACITY);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                THREAD_SIZE,THREAD_SIZE,keepAliveTime,unit,workQueue,handler);

        for (int i = 0; i < 10; i++) {
            RunnableTest runnableTest = new RunnableTest(CAPACITY);
            pool.execute(runnableTest);
        }

        pool.shutdown();
    }
    /**
     * start
     * task_1 is running
     * end
     * start
     * task_1 is running
     * end
     */
}

package com.luml.thread.Rejected;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author luml
 * @description:
 *  当有任务添加到线程池被拒绝时，线程池会将被拒绝的任务添加到”线程池正在运行的线程”中去运行。
 * @date 2021/6/26 07:43
 */
public class CallerRunsPolicy {

    private static final int THREAD_SIZE = 1;
    private static final int CAPACITY = 1;

    public static void main(String[] args) {

        long keepAliveTime = 0;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(CAPACITY);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        //创建线程池 拒绝策略为 CallerRunsPolicy
        ThreadPoolExecutor pool = new ThreadPoolExecutor
                (THREAD_SIZE,THREAD_SIZE,keepAliveTime,unit,workQueue,handler);

        //新建10个任务，并添加到线程池中
        for (int i=0;i<10;i++) {
            RunnableTest myrun = new RunnableTest(i);
            pool.execute(myrun);
        }

        //关闭线程池
        pool.shutdown();
    }
    /**
     * start
     * task_2 is running
     * end
     * start
     * task_0 is running
     * end
     * start
     * task_1 is running
     * end
     * start
     * task_3 is running
     * end
     * start
     * task_5 is running
     * end
     * start
     * task_6 is running
     * end
     * start
     * task_7 is running
     * end
     * start
     * task_8 is running
     * end
     * start
     * task_4 is running
     * end
     * start
     * task_9 is running
     * end
     */
}

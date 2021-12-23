package com.luml.thread.Rejected;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author luml
 * @description:
 *  当有任务添加到线程池被拒绝时，会抛出RejectedExecutionException。
 * @date 2021/6/26 07:41
 */
public class AbortPolicyDemo {

    public static final int THREADS_SIZE = 1;

    public static final int CAPACITY = 1;

    public static void main(String[] args) throws Exception {

        long keepAliveTime = 1;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(CAPACITY);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                THREADS_SIZE,THREADS_SIZE,keepAliveTime,unit,workQueue,handler);
        try {
            for (int i = 0; i < 10;i++ ){
                RunnableTest runnableTest = new RunnableTest(i);
                pool.execute(runnableTest);
            }
        }catch(Exception e){
            System.out.println("异常为："+e.toString());
        }finally {
            pool.shutdown();
        }
    }
    /**
     * start
     * task_0 is running
     * end
     * start
     * 异常为：java.util.concurrent.RejectedExecutionException:
     *      Task com.luml.thread.Rejected.RunnableTest@4c873330 rejected from java.util.concurrent.ThreadPoolExecutor@119d7047
     *      [Running, pool size = 1, active threads = 1, queued tasks = 1, completed tasks = 0]
     * task_1 is running
     * end
     */
}

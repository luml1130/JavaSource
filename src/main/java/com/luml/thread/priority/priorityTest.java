package com.luml.thread.priority;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author luml
 * @description
 * @date 2021/3/31 7:12 下午
 */
public class priorityTest {

    private static volatile boolean notStart=true;
    private static volatile boolean notEnd=true;

    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<>();
        /**循环启动10个线程**/
        for(int i = 0;i<10;i++){
            int priority = i<5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread=new Thread(job, "Thread:"+i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart=false;
        /**main线程沉睡10s，使得10个小线程执行结束**/
        TimeUnit.SECONDS.sleep(10);
        notEnd=false;
        for(Job job:jobs){
            System.out.println("JOB priority:"+job.priority+","+job.jobCount);
        }
    }

    static class Job implements Runnable{

        private int priority;
        private long jobCount;

        public Job(int priority){
            this.priority=priority;
        }

        @Override
        public void run(){
            while(notStart){
                //这里确保main线程将10个小线程启动成功
                Thread.yield();
            }
            while(notEnd){
                //这里让出CPU资源，使得10个线程自由竞争。
                Thread.yield();
                //记录竞争状态，反映线程的优先级。
                jobCount++;
            }
        }
    }
    /**
     * JOB priority:1,1216462
     * JOB priority:1,1233049
     * JOB priority:1,1233282
     * JOB priority:1,1250110
     * JOB priority:1,1278033
     * JOB priority:10,1253085
     * JOB priority:10,1223173
     * JOB priority:10,1212817
     * JOB priority:10,1234521
     * JOB priority:10,1224739
     * 让我们看一下Linux环境下的运行结果：
     * JOB priority:1,3075988
     * JOB priority:1,2899121
     * JOB priority:1,2843459
     * JOB priority:1,2780645
     * JOB priority:1,2910943
     * JOB priority:10,3243229
     * JOB priority:10,12090519
     * JOB priority:10,3027128
     * JOB priority:10,6275521
     * JOB priority:10,2995204
     * 从上面这个结果可以看出，操作系统并没有理会我们设置的线程优先级。
     */
}

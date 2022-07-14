package com.luml.threadPool.ThreadExecutor;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author luml
 * @description
 * @date 2020/8/9
 */
public class SingleThreadExecutorTest {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable(){
                @Override
                public void run(){
                    try{
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch(InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Test
    public void test1(){
        Runnable runnable = new Runnable() {
            int i = 1;

            public void run() {
                try {
                    System.out.println(i);
                    i++;
                    // i为5时发生by zero异常
                    if (i == 5) {
                        int a = 1 / 0;
                    }
                } catch (Exception e) {
                    System.out.println("发生异常");
                }

            };
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // TimeUnit.SECONDS 延时单位为秒
        service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * 使用单个线程的线程池，适用于需要使用单个后台线程执行周期任务，同时需要保证各个任务执行顺序的应用场景。
     */
    @Test
    public void test(){
        ScheduledExecutorService single = Executors.newSingleThreadScheduledExecutor();
        Runnable runnable1 = () -> {
            try {
                Thread.sleep(4000);
                System.out.println("11111111111111");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable runnable2 = () -> {
            try {
                Thread.sleep(4000);
                System.out.println("222");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        single.scheduleWithFixedDelay(runnable1,0,1, TimeUnit.SECONDS);
        single.scheduleWithFixedDelay(runnable2,0,2, TimeUnit.SECONDS);
    }

}

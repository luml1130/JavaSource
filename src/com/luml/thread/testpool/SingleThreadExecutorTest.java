package com.luml.thread.testpool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author luml
 * @description:
 *  保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
 * @date 2021/6/26 12:06
 */
public class SingleThreadExecutorTest {

    public static void main2(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 10; i++) {
            final  int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
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
    public void test(){
        Executor executor = Executors.newSingleThreadExecutor();
        //可以保证顺序执行
       /* executor.execute(new Thread(new ThreadA()));
        executor.execute(new Thread(new ThreadB()));
        executor.execute(new Thread(new ThreadC()));*/
    }

    /**
     * newFixedThreadPool(1)和newSingleThreadExecutor()的区别
     * 有序性:一样 都是先进先出
     * @throws Exception
     */
    @Test
    public  void testOrder() throws Exception{
        ExecutorService es1 = Executors.newFixedThreadPool(1);
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        testOrder(es1);
        TimeUnit.SECONDS.sleep(1);
        testOrder(es2);

    }
    private static void testOrder(ExecutorService es) throws InterruptedException{
        List<Integer> submit = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        IntStream.range(0,100).forEach((i) -> {
            submit.add(i);
            es.submit(() -> {
                result.add(i);
            });
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println(submit);
        System.out.println(result);
    }

    /**
     * newFixedThreadPool(1)和newSingleThreadExecutor()的区别
     * 异常恢复：两个线程池都能够从异常中恢复。可见这两个方法的异常恢复功能都是一样的。
     * @throws Exception
     */
    /*@Test
    public  void testException() throws Exception{
        ExecutorService es1 = Executors.newFixedThreadPool(1);
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        testThreadCrash(es1);
        TimeUnit.SECONDS.sleep(1);
        testThreadCrash(es2);
    }

    private static void testThreadCrash(ExecutorService es) {
        es.submit(() -> {
            throw new IllegalStateException("Error");
        });
        es.submit(() -> {
            System.out.println("running ... ");
        });
    }*/
}​
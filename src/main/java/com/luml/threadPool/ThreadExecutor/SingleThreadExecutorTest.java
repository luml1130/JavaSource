package com.luml.threadPool.ThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
}

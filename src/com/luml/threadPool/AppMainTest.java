package com.luml.threadPool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luml
 * @description: ThreadLocal和ConcurrentHashMap的不同用法
 * @date 2020/4/28 23:03
 */
public class AppMainTest {
    private static ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String, String>();
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>(){
        @Override
        protected Object initialValue() {
            return "初始化值";
        }
    };

    public static void main(String[] args) {
        new Thread("Thread1"){
            @Override
            public void run() {
                threadLocal.set(Thread.currentThread().getName());
                map.put(Thread.currentThread().getName(), Thread.currentThread().getName());
            }
        }.start();

        new Thread("Thread2"){
            @Override
            public void run() {
                threadLocal.set(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName()+"==="+threadLocal.get());
                map.put(Thread.currentThread().getName(), Thread.currentThread().getName());
            }
        }.start();

        new Thread("Thread3"){
            @Override
            public void run() {
                threadLocal.set(Thread.currentThread().getName());
                map.put(Thread.currentThread().getName(), Thread.currentThread().getName());
            }
        }.start();

        System.out.println(Thread.currentThread().getName()+"==="+threadLocal.get());
        for (Map.Entry<String, String> item : map.entrySet()) {
            System.out.println(item.getKey()+"==="+item.getValue());
        }
    }
    /**
     * Thread2===Thread2
     * main===初始化值
     * Thread3===Thread3
     * Thread2===Thread2
     * Thread1===Thread1
     */

}

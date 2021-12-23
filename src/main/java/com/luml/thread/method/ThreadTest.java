package com.luml.thread.method;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author luml
 * @description:
 *  工作中使用 ExecuteService.execute(Runnable runnable)方法 进行多线程的数据插入，
 *                  出现部分未执行，数据没有进入数据库。
 *  后改为Future future = ExecuteService.submit(Callable task)方法后未出现前面的BUG。
 * @date 2021/6/26 09:33
 */
public class ThreadTest {

    final String column1 = "id";
    final String column2 = "name";

    @Test
    public void runThread() {
        // 将map当作一个entity
        List<Map<String, String>> dataList = new LinkedList<>();
        prepareData(dataList);

        System.out.println("开始执行...");

        //executeRunnable(dataList);
        executeFuture(dataList);

        System.out.println("结束执行...");

    }
    private void executeRunnable(List<Map<String, String>> dataList) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        dataList.forEach(map -> {
            Runnable runnable = () -> {
                try {
                    // 睡眠当作执行了业务代码
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("threadId: %s, id: %s, name: %s%n", Thread.currentThread().getId(), map.get(column1), map.get(column2));
            };
            executorService.execute(runnable);
        });
    }

    private void executeFuture(List<Map<String, String>> dataList) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<String>> futureList = new LinkedList<>();
        dataList.forEach(map -> {
            Callable<String> callable = () -> {
                try {
                    // 睡眠当作执行了业务代码
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("threadId: %s, id: %s, name: %s%n", Thread.currentThread().getId(), map.get(column1), map.get(column2));
                return "Success";
            };
            Future<String> future = executorService.submit(callable);
            futureList.add(future);
        });
        futureList.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private void prepareData(List<Map<String, String>> dataList) {
        int size = 10;
        for (int i = 0; i < size; i++) {
            Map<String, String> data = new HashMap<>();
            data.put(column1, i + "");
            data.put(column2, column2 + i);
            dataList.add(data);
        }
    }

}

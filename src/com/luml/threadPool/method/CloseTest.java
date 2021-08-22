package com.luml.threadPool.method;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luml
 * @description
 * @date 2021/4/1 1:46 下午
 */
public class CloseTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(50);
        for (int i = 1; i <= 5; i++) {
            exe.execute(new SubThread(i));
        }
        exe.shutdown();
        /**
         * 通过while（true）循环判断exe.isTerminated() 重生之大文豪 的值，为了防止过多的判断浪费资源，可设置线程睡眠Thread.sleep(200);
         * 正是由于这个睡眠，所以当所有线程池中的线程都执行完后，有可能延迟200ms才执行"结束了"语句。这个参数越小延迟越小，结果越准确。
         */
        while (true) {
            if (exe.isTerminated()) {
                System.out.println("结束了！");
                break;
            }
            Thread.sleep(200);
        }
        /**
         * 上面是主线程的代码，创建了一个能同时执行2个线程的线程池，并投入5个线程，当5个线程都执行完毕后打印---“结束了！”字符串。
         * 1
         * 3
         * 2
         * 4
         * 5
         * 结束了！
         * 子线程执行顺序不能控制，所以输出的结果是乱序的。
         */
    }
}

class SubThread extends Thread{
    private final int i;
    public SubThread(int i){
        this.i = i;
    }
    @Override
    public void run(){
        System.out.println(i);
    }
}  

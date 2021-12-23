package com.luml.juc.synchronizedTest;

/**
 * @author luml
 * @description:
 * 尝试将synchronizrd去掉，看看结果得到的是不是1000
 * @date 2021/8/22 下午5:17
 */
public class Demo {


    private static int count=0;


    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<1000;i++){
            new Thread(()->Demo.inc()).start();
        }
        Thread.sleep(3000);
        System.out.println("运行结果"+count);
    }

    public static void inc(){
        synchronized (Demo.class) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

}

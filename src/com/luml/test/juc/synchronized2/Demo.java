package com.luml.test.juc.synchronized2;

/**
 * @author luml
 * @description：
 *  https://blog.csdn.net/baidu_38083619/article/details/82527461
 * @date 2021/7/28 下午10:17
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

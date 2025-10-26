package com.luml.thread.base;

/**
 * @author luml
 * @description: 线程安全的，不共享数据：每个线程访问各自的实例变量。
 * 共创建3个线程，每个线程都有各自的 count 变量，各自处理自己的count变量值
 *  （根据Java实例的特性，每new一个MyThread实例，其实例地址都不相同，对应的a、b、c三个的线程处理的都是自己的实例里的数据 ）。
 * @date 2025/10/26
 */
public class ThreadSafeTest01 {
    public static void main(String[] args) {
        Thread01 a = new Thread01("A");
        Thread01 b = new Thread01("B");
        Thread01 c = new Thread01("C");
        a.start();
        b.start();
        c.start();
    }
}

class Thread01 extends Thread {
    private int count = 5;
    public Thread01(String name) {
        super();
        //设置线程名称
        this.setName(name);
    }
    @Override
    public void run() {
        super.run();
        while(count > 0) {
            count--;
            System.out.println("由 "+ currentThread().getName()
                    + " 计算，count=" +count);
        }
    }
}



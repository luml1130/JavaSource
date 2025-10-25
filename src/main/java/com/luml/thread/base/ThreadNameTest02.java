package com.luml.thread.base;

/**
 * @author luml
 * @description
 *  - **多种设置方式**：提供了构造方法、setName()方法和Runnable实现三种设置线程名称的方式
 *         - **名称获取方法**：演示了getName()和Thread.currentThread().getName()的使用场景
 *         - **主线程处理**：展示了如何设置和获取主线程的名称
 *         - **线程标识作用**：线程命名对于程序调试和多线程管理有重要作用
 *
 *         ### 注意事项
 *         - 如果没有显式设置线程名称，系统会自动为线程分配默认名称（如"Thread-0"、"Thread-1"等）
 *         - 在实现Runnable接口时，必须使用Thread.currentThread().getName()来获取线程名称，不能使用this.getName()
 *         - 建议在线程启动前设置名称，以确保线程标识的准确性.
 * 打印结果如下：
 * 线程名称: 自定义线程-1
 * 当前线程: 自定义线程-1
 * 线程名称: 自定义线程-2
 * 当前线程: 自定义线程-2
 *
 * 当前主线程: 主线程
 *
 * 任务名称: 任务A
 * 当前线程: 工作线程-1
 *
 * 任务名称: 任务B
 * 当前线程: 工作线程-2
 * Process finished with exit code 0
 * @date 2025/10/25
 */
public class ThreadNameTest02 {
    public static void main(String[] args) {
        // 方式1: 通过构造方法设置名称
        MyThread02 thread1 = new MyThread02("自定义线程-1");
        thread1.start();

        // 方式2: 通过setName方法设置名称
        MyThread02 thread2 = new MyThread02("");
        thread2.setName("自定义线程-2");
        thread2.start();

        // 方式3: 实现Runnable接口
        Thread thread3 = new Thread(new MyRunnable("任务A"), "工作线程-1");
        thread3.start();

        // 获取主线程名称
        Thread mainThread = Thread.currentThread();
        mainThread.setName("主线程");
        System.out.println("当前主线程: " + mainThread.getName());

        // 演示Thread.currentThread()与this的区别
        Thread thread4 = new Thread(new MyRunnable("任务B"));
        thread4.setName("工作线程-2");
        thread4.start();
    }
}


// 继承Thread类方式
class MyThread02 extends Thread {
    public MyThread02(String name) {
        super(name); // 通过构造方法设置线程名称
    }
    @Override
    public void run() {
        System.out.println("线程名称: " + getName());
        System.out.println("当前线程: " + Thread.currentThread().getName());
    }
}

// 实现Runnable接口方式
class MyRunnable implements Runnable {
    private String taskName;

    public MyRunnable(String taskName) {
        this.taskName = taskName;
    }
    @Override
    public void run() {
        System.out.println("任务名称: " + taskName);
        System.out.println("当前线程: " + Thread.currentThread().getName());
    }
}
package com.luml.thread.group;

public class ThreadGroupDemo {

    public static void main(String[] args) throws InterruptedException {
        // 1. 创建一个新的线程组
        ThreadGroup taskGroup = new ThreadGroup("数据处理任务组");
        
        // 2. 设置线程组为守护线程组（可选）
        // 当组内所有线程都结束时，如果只有守护线程，JVM可能会退出
        taskGroup.setDaemon(true);

        // 3. 创建并启动属于该组的线程
        Runnable task = () -> {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " 正在处理数据: " + i);
                    Thread.sleep(1000); // 模拟耗时操作
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 被中断，正在清理资源...");
                // 这里可以添加清理逻辑
            }
        };

        Thread thread1 = new Thread(taskGroup, task, "Worker-1");
        Thread thread2 = new Thread(taskGroup, task, "Worker-2");
        Thread thread3 = new Thread(taskGroup, task, "Worker-3");

        thread1.start();
        thread2.start();
        thread3.start();

        // 4. 监控线程组状态
        System.out.println("活跃线程数: " + taskGroup.activeCount());
        System.out.println("线程组名称: " + taskGroup.getName());
        
        // 让主线程等待3秒，然后演示批量中断
        Thread.sleep(3000);
        
        System.out.println("\n--- 主线程发出中断指令 ---");
        // 5. 批量中断线程组内的所有线程
        taskGroup.interrupt();
        
        // 等待线程结束
        thread1.join();
        thread2.join();
        thread3.join();
        
        System.out.println("所有线程已结束。");
    }
}

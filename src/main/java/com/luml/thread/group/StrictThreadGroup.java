package com.luml.thread.group;

/**
 * @author luml
 * @description
 * 如何使异常影响其他线程？
 * 虽然默认不影响，但开发者可以通过重写 ThreadGroup 的行为来改变这一机制。
 * 例如，可以创建一个自定义线程组，当其中任何一个线程发生未捕获异常时，强制中断组内所有其他线程：
 * @date 2026/5/21
 */
public class StrictThreadGroup extends ThreadGroup{

    public StrictThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //super.uncaughtException(t, e);
        System.out.println("线程 " + t.getName() + " 发生异常，中断组内所有线程！");
        // 关键操作：中断组内所有活跃线程
        this.interrupt();
        super.uncaughtException(t, e);
    }

}

package com.luml.thread.group;

/**
 * @author luml
 * @description
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

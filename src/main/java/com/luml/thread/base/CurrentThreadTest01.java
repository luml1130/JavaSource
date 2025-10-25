package com.luml.thread.base;

/**
 * @author luml
 * @description
 * @date 2025/10/25
 */
public class CurrentThreadTest01 {

    /**
     *  Thread.currentThread().getName()和this‌.getName()的区别：
     *  在构造方法中调用时，两者通常指向不同对象。
     *          例如在 main 线程中创建线程对象时，Thread.currentThread() 返回的是 main 线程，
     *          而 this.getName() 返回的是新线程对象的默认名称 "Thread-0"。
     *  在 run 方法中，当直接调用 run() 方法时，两者指向不同；
     *          但当通过 start() 方法启动新线程时，两者指向同一个线程对象。
     *          特别是当使用 Thread(Runnable target) 构造器创建线程时，
     *                   Thread.currentThread() 返回的是新创建的线程，
     *                    而 this 仍然指向作为 target 的 Runnable 对象。
     *  //TODO   ？？？？ 这个需要再学习
     * CountOperate---begin
     * CountOperate Construction Thread.currentThread().getName()=main
     * CountOperate Construction this.getName() =Thread-0
     * CountOperate---end
     *
     * run---begin
     * CountOperate run Thread.currentThread().getName()=A
     * CountOperate run this.getName() =Thread-0
     * run---end
     * @param args
     */
    public static void main(String[] args) {
        CountOperate countOperate = new CountOperate();
        Thread myThread = new Thread(countOperate);
        myThread.setName("A");
        myThread.start();
    }

    /**
     * 2、构造方法是main线程调用 run方法是被名为Thread-0的线程调用，run方法是自动调用的方法
     * 构造方法的打印=main，run方法的打印+=Thread-0
     * @param args
     */
    /*public static void main(String[] args) {
        MyThread01 myThread01 = new MyThread01();
        myThread01.start();
    }*/

    //1、只有一个main线程
   /* public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        //打印结果是 main
    }*/
}
class MyThread01 extends  Thread{
    public MyThread01() {
        System.out.println("构造方法的打印="+Thread.currentThread().getName());

    }
    @Override
    public void run() {
        System.out.println("run方法的打印="+Thread.currentThread().getName());
    }
}

class CountOperate extends  Thread{
    public CountOperate() {
        System.out.println("CountOperate---begin");
        System.out.println("CountOperate Construction Thread.currentThread().getName()="+Thread.currentThread().getName());
        System.out.println("CountOperate Construction this.getName() ="+this.getName());
        System.out.println("CountOperate---end");

    }

    @Override
    public void run() {
        System.out.println("run---begin");
        System.out.println("CountOperate run Thread.currentThread().getName()="+Thread.currentThread().getName());
        System.out.println("CountOperate run this.getName() ="+this.getName());
        System.out.println("run---end");
    }
}
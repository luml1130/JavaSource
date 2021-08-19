package com.luml.thread.daemon;

/**
 * @author luml
 * @description
 * @date 2021/4/1 11:33 上午
 */
public class DaemonTest2 {

    public static void main(String[] args) {
        Thread t1 = new MyCommon();
        Thread t2 = new Thread(new MyCommon());
        //设置为守护线程
        t2.setDaemon(true);
        t2.start();
        t1.start();
    }
    /**
     * 如果只有t1
     * 线程1第0次执行！
     * 线程1第1次执行！
     * 线程1第2次执行！
     * 线程1第3次执行！
     * 线程1第4次执行！
     *
     * 如果全部打开：
     * 线程1第0次执行！
     * 线程1第0次执行！
     * 线程1第1次执行！
     * 线程1第1次执行！
     * 线程1第2次执行！
     * 线程1第2次执行！
     * 线程1第3次执行！
     * 线程1第3次执行！
     * 线程1第4次执行！
     * 线程1第4次执行！
     * Process finished with exit code 0
     * 从上面的执行结果可以看出：前台线程是保证执行完毕的，后台线程还没有执行完毕就退出了。
     */
}

class MyCommon extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
        	System.out.println("线程1第" + i + "次执行！");
        	try {
        	    Thread.sleep(7);
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        }
    }
}
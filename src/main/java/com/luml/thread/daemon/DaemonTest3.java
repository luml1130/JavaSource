package com.luml.thread.daemon;

/**
 * @author luml
 * @description
 * @date 2021/4/1 11:47 上午
 */
public class DaemonTest3 {
    public static void main(String[] args) {
        //使用守护线程监控用户线程的状态变化
        MyUserThread userThread = new MyUserThread();
        MyDaemonThread daemonThread = new MyDaemonThread(userThread);
        userThread.start();
        daemonThread.start();
    }

    /**
     * 用户线程
     * @author Administrator
     *	每0.1秒增加1
     */
    static class MyUserThread extends Thread{
        int i = 0;
        public int getI(){
            return i;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            while(i < 10){
                System.out.println("--用户--："+getI());
                i++;
                try {
                    sleep(100);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    }

    /**
     * 守护线程
     * @author Administrator
     *	检测 用户线程的数值变化
     */
    static class MyDaemonThread extends Thread{
        private MyUserThread userThread;
        private int tmp;
        public MyDaemonThread(MyUserThread uthread){
            this.userThread = uthread;
            this.tmp = uthread.getI();
            setDaemon(true);
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            while(true){
                try {
                    sleep(50);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                if(tmp!=userThread.getI()){
                    System.out.println("用户线程变化了");
                    tmp = userThread.getI();
                }
                System.out.println(userThread.i);
            }
        }

    }

}

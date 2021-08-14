package com.luml.jvm.tool;

/**
 * @author luml
 * @description:
 * 这是一个死锁的例子：对应有道：1-0JDK命令行工具
 * jstack 的其中一个应用场景便是死锁检测。这里我用jstack获取一个已经死锁了的 Java 程序的栈信息。
 * jps -l
 * jstack -l 2684
 * @date 2020/9/5
 */
public class jstackTest {
    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
    }
}

class ThreadResource {
    public static Object resource1 = new Object();
    public static Object resource2 = new Object();
}

class Thread1 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Thread1 is running");
            synchronized (ThreadResource.resource1) {
                System.out.println("Thread1 lock resource1");
                Thread.sleep(2000);//休眠2s等待线程2锁定资源2
                synchronized (ThreadResource.resource2) {
                    System.out.println("Thread1 lock resource2");
                }
                System.out.println("Thread1 release resource2");
            }
            System.out.println("Thread1 release resource1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Thread1 is stop");
    }
}

class Thread2 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Thread2 is running");
            synchronized (ThreadResource.resource2) {
                System.out.println("Thread2 lock resource2");
                Thread.sleep(2000);//休眠2s等待线程1锁定资源1
                synchronized (ThreadResource.resource1) {
                    System.out.println("Thread2 lock resource1");
                }
                System.out.println("Thread2 release resource1");
            }
            System.out.println("Thread2 release resource2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Thread2 is stop");
    }
}

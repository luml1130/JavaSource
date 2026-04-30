package com.gof.create.create.single;

import com.luml.juc.lock.keyword.synchronized2.TestSynchronized;
import lombok.Synchronized;

/**
 * 首选：懒汉式单例方法2：双重检查锁定
 *       uem也是这样做的
 */
public  class YoudaoTest{
    public static void main(String[] args) {
       lSingleton2 single3 = lSingleton2.getInstance();
       lSingleton2 single4 = lSingleton2.getInstance();
       System.out.println(single3 == single4);
    }
}

/**
 * 饿汉式单例类1.在类初始化时，已经自行实例化
 * 饿汉式在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以天生是线程安全的。
 */
class eSingleton1 {
    private eSingleton1() {}
    //private static final eSingleton1 single = new eSingleton1();
    private static eSingleton1 single = null; //注意这是private 只供内部调用
    public static eSingleton1 getInstance() {    //静态工厂方法
        if (single == null) {
            single = new eSingleton1();
        }
        return single;
    }
}

/**
 * 懒汉式单例方法1：在getInstance方法上加同步
 * 注意这是private 只供内部调用
 *  //静态工厂方法    //这里提供了一个供外部访问本class的静态方法，可以直接访问　
 */
class lSingleton1 {
    private lSingleton1() {}
    private static lSingleton1 single=null;
    //静态工厂方法,这里提供了一个供外部访问本class的静态方法，可以直接访问　
    public static synchronized lSingleton1 getInstance() {
        if (single == null) {
            single = new lSingleton1();
        }
        return single;
    }
}

/**
 * 懒汉式单例方法2：双重检查锁定
 * 问题：既然synchronized已经起到了多线程下原子性、有序性、可见性的作用，为什么还要加volatile呢？-->有道
 * 解答：如果不正确地使用synchronized关键字，可能会导致线程安全问题。
 *   uniqueInstance = new Singleton();分3不执行 会被指令重排序，
 *   使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
 * 所以：通过将一个变量声明为volatile，可以确保在多线程环境下安全地实现双重检查锁定。
 */
class lSingleton2 {
    private lSingleton2() {}
   // private static lSingleton2 single=null;
    private static volatile lSingleton2 single;
    public static lSingleton2 getInstance() {
        if(single == null){
            synchronized (lSingleton2.class){
                if(single == null ){
                    single = new lSingleton2();
                }
            }
        }
        return single;
    }
}

/**
 * 懒汉式单例方法3：静态内部类
 */
class lSingleton3{
    private lSingleton3() {}
    private static class LazyHolder {
        private static final lSingleton3 INSTANCE = new lSingleton3();
    }
    public static final lSingleton3 getInstance() {
        return LazyHolder.INSTANCE;
    }
}

/*class Test{
    private Test(){};
    private static volatile Test test2;
    public static Test create(){
        if(test2 == null){
            synchronized (Test.class) {
                if(test2 == null) {
                    test2 = new Test();
                }
            }
        }
        return test2;
    }
}*/

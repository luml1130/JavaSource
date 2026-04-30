package com.gof.create.create.single;

/**
 * @author luml
 * @description
 *  form:Java设计模式及实践
 * @date 2020/4/21 23:14
 */
public class Main {
    public static void main(String[] s) {
        shuSingleton singleton = shuSingleton.getInstance();
    }
}

class shuSingleton{
    private static shuSingleton instance;
    private shuSingleton(){
        System.out.println("Singleton is Instantiated.");
    }
    public static shuSingleton getInstance()    {
        if (instance == null) {
            instance = new shuSingleton();
        }
        return instance;
    }
    public void doSomething(){
        System.out.println("Something is Done.");
    }
}

class LockFreeSingleton{
    private static final LockFreeSingleton instance = new LockFreeSingleton();

    private LockFreeSingleton(){
        System.out.println("Singleton is Instantiated.");
    }

    public static synchronized LockFreeSingleton getInstance(){
        return instance;
    }

    public void doSomething(){
        System.out.println("Something is Done.");
    }
}

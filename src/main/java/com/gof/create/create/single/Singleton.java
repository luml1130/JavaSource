package com.gof.create.create.single;

public class Singleton {
    /*private Singleton(){};
    private static  Singleton singleton= null;
    public static Singleton getSingleton(){
        if(singleton == null ){
            singleton = new Singleton();
        }
        return singleton;
    }*/

    //在getInstance方法上加同步
    /*private Singleton(){};
    private static  Singleton singleton= null;
    public static synchronized Singleton getSingleton(){
        if(singleton == null ){
            singleton = new Singleton();
        }
        return singleton;
    }*/

    //双重检查
   /* private Singleton(){};
    private static volatile Singleton singleton= null;
    public static synchronized Singleton getSingleton(){
        if(singleton == null ){
            synchronized (Singleton.class){
                singleton = new Singleton();
            }
        }
        return singleton;
    }*/

   //静态内部类
    private Singleton(){};
    private static class LazyHolder{
        private static final Singleton  Instance = new Singleton();
    }
    public static final Singleton getSingleton(){
        return LazyHolder.Instance;
    }
}

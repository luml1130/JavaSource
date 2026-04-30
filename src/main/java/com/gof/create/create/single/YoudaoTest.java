package com.gof.create.create.single;

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
 * 懒汉式单例方法1：在getInstance方法上加同步
 * 注意这是private 只供内部调用
 *  //静态工厂方法    //这里提供了一个供外部访问本class的静态方法，可以直接访问　
 */
class lSingleton1 {
    private lSingleton1() {}
    private static lSingleton1 single=null;
    public static synchronized  lSingleton1 getInstance() {
        if (single == null) {
            single = new lSingleton1();
        }
        return single;
    }
}

/**
 * 懒汉式单例方法2：双重检查锁定
 */
class lSingleton2 {
    private lSingleton2() {}
    private static lSingleton2 single=null;
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

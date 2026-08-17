package com.luml.gof._1_create.single;

/**
 * @author luml
 * @description 静态内部类
 *  这种比上面1、2都好一些，既实现了线程安全，又避免了同步带来的性能影响。
 * @date 2020/11/26
 */
public class C_1_Singleton_03 {
    private static class LazyHolder {
        private static final C_1_Singleton_03 INSTANCE = new C_1_Singleton_03();
    }
    private C_1_Singleton_03 (){}

    public static final C_1_Singleton_03 getInstance() {
        return LazyHolder.INSTANCE;
    }
}

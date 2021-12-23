package com.luml.java.base;

import com.luml.thread.other.DeadLock2;

/**
 * @author luml
 * @description
 * @date 2021/8/22 下午6:07
 */
public class A {
    public void instanceMethod () {
        System.out.println("这是一个实例方法。");
    }
    public static void staticMethod () {
        System.out.println("这是一个静态方法。");
        //instanceMethod(); //这样是错的。
        new A().instanceMethod(); //只有这样才对。
    }
}
class B {
    public void callInstanceMethod () {
        new A().instanceMethod(); //调用一个 A 实例的实例方法。
        A.staticMethod(); //调用一个 A 的静态方法。
    }
}

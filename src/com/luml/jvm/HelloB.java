package com.luml.jvm;

/**
 * @author luml
 * @description: 对象的初始化顺序：（1）类加载之后，按从上到下（从父类到子类）执行被static修饰的语句；
 *          （2）当static语句执行完之后,再执行main方法；
 *          （3）如果有语句new了自身的对象，将从上到下执行构造代码块、构造器。
 * @date 2020/4/28 18:55
 */
public class HelloB extends HelloA {
    static {
        System.out.println("static B");
    }
    {
        System.out.println("I'm B class");
    }
    public HelloB() {
        System.out.println("HelloB");
    }

    public static void main(String[] args) {
        new HelloB();
    }
    /**
     * static A
     * static B
     * I'm A class
     * HelloA
     * I'm B class
     * HelloB
     */
}

class HelloA {
    static {
        System.out.println("static A");
    }
    {
        System.out.println("I'm A class");
    }
    public HelloA() {
        System.out.println("HelloA");
    }
}
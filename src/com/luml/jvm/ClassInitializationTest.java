package com.luml.jvm;

/**
 * @author luml
 * @description
 * 类：初始化例子
 * @date 2020/4/28 12:16
 */
public class ClassInitializationTest {
    public static void main(String[] args) {
        //this class is not used, should not be initialized
        NotUsed o = null;
        //initializing sub class, should trigger super class initialization
        Child t = new Child();
        System.out.println((Object) o == (Object)t);
    }
    /**
     * static block of Super class is initialized
     * static block of Sub class is initialized in Java
     * non static blocks in super class is initialized
     * non static blocks in sub class is initialized
     * false
     */
}

/**
 * Super class to demonstrate that Super class is loaded and initialized before Subclass.
 */
class Parent {
    static{
        System.out.println("static block of Super class is initialized");
    }
    {
        System.out.println("non static blocks in super class is initialized");
    }
 }

/**
 * Java class which is not used in this program, consequently not loaded by JVM
 */
class NotUsed {
    static{
       System.out.println("NotUsed Class is initialized ");
    }
}

/**
 * Sub class of Parent, demonstrate when exactly sub class loading and initialization occurs.
 */
class Child extends Parent {
    static{
        System.out.println("static block of Sub class is initialized in Java ");
    }
    {
        System.out.println("non static blocks in sub class is initialized");
    }
}

package com.luml.jvm.init;

/**
 * @author luml
 * @description:
 * Another Java program example to demonstrate class initialization and loading in Java.
 * @date 2020/4/28 12:20
 */
public class ClassInitializationTest2 {
    public static void main(String[] args) {
        //accessing static field of Parent through child, should only initialize Parent
        System.out.println(Child2.familyName);
        /**
         * static block of Super class is initialized
         * Lawson
         */
    }
}

class Parent2 {
    /**
     * compile time constant, accessing this will not trigger class initialization
     * protected static final String familyName = "Lawson";
     */
    protected static String familyName = "Lawson";
    static{
        System.out.println("static block of Super class is initialized");
    }
    {
        System.out.println("non static blocks in super class is initialized");
    }
}

class Child2 extends Parent2 {
    static{
        System.out.println("static block of Sub class is initialized in Java ");
    }
    {
        System.out.println("non static blocks in sub class is initialized");
    }
}

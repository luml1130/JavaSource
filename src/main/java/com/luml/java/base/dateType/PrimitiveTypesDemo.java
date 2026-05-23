package com.luml.java.base.dateType;

import org.junit.Test;

/**
 * @author luml
 * @description
 *  基本数据类型（Primitive Types）
 * @date 2026/5/23
 */
public class PrimitiveTypesDemo {

    static int primitiveVar;      // 默认值 0
    static String referenceVar;   // 默认值 null
    //默认值与 Null
    public static void main(String[] args) {
        System.out.println(primitiveVar); // 输出 0
        // System.out.println(referenceVar.length()); // 报错！NullPointerException，因为它是 null
        System.out.println(referenceVar); // 输出 null
    }

    /**
     * 存储与赋值的区别
     */
    @Test
    public void test(){
        // 基本数据类型：值拷贝
        int a = 10;
        int b = a; // b 复制了 a 的值 10
        b = 20;    // 修改 b，a 不受影响
        System.out.println(a); // 输出 10

        // 引用数据类型：引用拷贝
        String s1 = new String("Hello");
        String s2 = s1; // s2 复制了 s1 的地址，指向堆中同一个 "Hello" 对象

        // 注意：String 是不可变类，直接赋值 s2 = "World" 会改变 s2 的指向，而不是修改原对象。
        // 为了演示引用特性，我们使用 StringBuilder（可变对象）：
        StringBuilder sb1 = new StringBuilder("Hi");
        StringBuilder sb2 = sb1; // sb2 和 sb1 指向堆中同一个对象
        sb2.append(" there");    // 通过 sb2 修改对象内容
        System.out.println(sb1); // 输出 "Hi there"，因为 sb1 也指向该对象
    }

    /**
     * 比较的区别
     */
    @Test
    public void compareTest2(){
        Integer x = new Integer(100);
        Integer y = new Integer(100);

        // 基本类型 int 比较
        int a = 100;
        int b = 100;
        System.out.println(a == b); // true (比较数值)

        // 引用类型 Integer 比较
        System.out.println(x == y);       // false (比较地址，两个不同的对象)
        System.out.println(x.equals(y));  // true  (比较内容，推荐使用 equals)

    }
}

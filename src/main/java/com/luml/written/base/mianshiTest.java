package com.luml.written.base;

import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2026/6/8
 */
public class mianshiTest {
    //1 单例
    private static volatile mianshiTest ins;
    private mianshiTest(){};
    public mianshiTest creat(){
        if(ins == null) {
            synchronized (this) {
                if (ins == null) {
                    ins = new mianshiTest();
                }
            }
        }
        return ins;
    }
    //一、 基础语法与运算符陷阱


    //3. 方法重载与 null
    @Test
    public void reLoadTest(){
       // public void myMethod(String str) { System.out.println("String"); }
        //public void myMethod(Object obj) { System.out.println("Object"); }

        // 调用
       // myMethod(null);
        /**
         * 解析‌：
         * Java 在重载方法选择时，会选择‌最具体‌（Most Specific）的参数类型。
         * String 是 Object 的子类，因此 null 优先匹配 String 类型的方法。
         * 如果存在两个无继承关系的引用类型（如 String 和 Integer）同时作为重载参数，传入 null 会导致编译错误（模糊调用）。
         * ‌答案‌：String
         */
    }






}

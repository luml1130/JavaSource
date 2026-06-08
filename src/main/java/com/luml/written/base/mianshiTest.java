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
    @Test
    //1. 自增运算符优先级
    public void test(){
        int k = 0;
        int ret = ++k + k++ + ++k + k;
        System.out.println(ret);
        /**
         * ++k：先自增再取值。k 变为 1，取值为 1。
         * k++：先取值再自增。取值为 1，k 变为 2。
         * ++k：先自增再取值。k 变为 3，取值为 3。
         * k：当前 k 为 3，取值为 3。
         * 计算：1 + 1 + 3 + 3 = 8。
         */
    }
    //2. 字符串连接与运算优先级
    @Test
    //todo 2个int+--字符串？？
    public void yunSuanTest(){
        int i1 = 10, i2 = 10;
        System.out.println("i1 + i2 = " + i1 + i2);//20
        System.out.println("i1 * i2 = " + i1 * i2);//100
        // System.out.println("i1 - i2 = " + i1 - i2); // 编译错误
        /**
         * 第一行：+ 从左到右执行。 "i1 + i2 = " + 10 变成字符串 "i1 + i2 = 10"，再 + 10 变成 "i1 + i2 = 1010"。
         * 第二行：* 优先级高于 +。先计算 10 * 10 = 100，然后 "i1 * i2 = " + 100 变成 "i1 * i2 = 100"。
         * 第三行（注释部分）：- 不能用于字符串和数字之间，会编译报错。
         * ‌答案‌：
         * i1 + i2 = 1010
         * i1 * i2 = 100
         */
    }

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

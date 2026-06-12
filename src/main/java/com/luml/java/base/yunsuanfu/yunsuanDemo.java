package com.luml.java.base.yunsuanfu;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luml
 * @description
 * @date 2026/6/12
 */
public class yunsuanDemo {

    /**
     * i = integer.incrementAndGet(); //先+1,然后在返回值,相当于++i  		System.out.println(i);   //2
     * i = integer.getAndIncrement();//先返回值,然后在+1,相当于i++		System.out.println(i);  //2
     */
    @Test
    public void incrementAndGet(){
        AtomicInteger atomicInt = new AtomicInteger(1);
        // 执行自增，返回自增后的值
        int newValue = atomicInt.incrementAndGet();//先+1,然后在返回值,相当于++i
        System.out.println(newValue); //2

        AtomicInteger atomicInt2 = new AtomicInteger(1);
        int newValue2 =  atomicInt2.getAndIncrement();//先返回值,然后在+1,相当于i++
        System.out.println(newValue2); //1

    }


    @Test
    public void Test2(){
        int i=1;
        int j=i++;//运行后i=2 j=1
        if((i == (++j)) && ((i++) == j)){
            //1 i==(++j);//i=2  (++j)先计算再赋值=2 true  j=2
            //2 (i++)==j;// (i++) 先引用后计算(i++)=2 j=2 true  i=3
            //运行后i=3 j=2
            i +=j;
        }
        System.out.println(i);//5
    }

    @Test
    public void Test1(){
        int i=1;
        int j=i++;//i=2 j=1

        System.out.println(i==(++j));//i=2 (++j)先计算后赋值 j=2 true
        System.out.println((i++)==j);//(i++)先赋值后计算=2,j=2 i=3

        if((i == (++j)) && ((i++) == j)){
            //1 i==(++j);//i=3  (++j)先计算后赋值=3 true  j=3
            //2 (i++)==j;// i++ 先赋值后计算(i++)=3 j=3 true   i=4
            i +=j;
        }
        System.out.println(i);//7
    }


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
}


package com.luml.java.javaclass;

import java.math.BigDecimal;

/**
 * @author luml
 * @description: 对比
 * @date 2020/8/16
 */
public class CompareTest {


    public static void main(String[] args) {
        compareTest();
    }

    /**
     * 如上所示 BigDecimal 的等值比较应使用 compareTo()方法，而不是 equals()方法。
     * 说明：equals()方法会比较值和精度（1.0 与 1.00 返回结果为 false），而 compareTo()则会忽略精度。
     */
    public static  void compareTest(){
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("1.00");
        System.out.println(a.equals(b)); //false
        System.out.println(a.compareTo(b) == 0); //true
    }

}

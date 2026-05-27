package com.luml.juc.lock.keyword.volatileTest;

import org.junit.Test;

/**
 * @author luml
 * @description
 * 问题引入：
 *   条件：一个线程不停的调用方法one()，一个线程不停的调用方法two()。
 *            结果偶尔会出现j大于i的情况，因为方法没有同步，所以会出现i和j可能不是一次更新。
 * @date 2026/5/26
 */
public class VolatileDemo1 {

    static int i = 0, j = 0;
    static void one() {
        i++;
        j++;
    }
    static void two() {
        System.out.println("i=" + i + " j=" + j);
    }

    /**
     * 方法一：
     * 防止这种情况发生的办法就是声明两个方法为synchronized 的。
     * 这样可以防止两个方法同时被执行，还可以保证j和i被同时更新，这样一来i和j的值一直是一样的。
     */
    static int i1 = 0, j1 = 0;
    static synchronized void one1() {
        i1++;
        j1++;
    }
    static synchronized void two1() {
        System.out.println("i1=" + i1 + " j1=" + j1);
    }

    /**
     * 方法二：把i和j声明为volatile。
     */
    static volatile int i2 = 0, j2 = 0;
    static void one2() {
        i2++;
        j2++;
    }
    static void two2() {
        System.out.println("i2=" + i2 + " j2=" + j2);
    }
}

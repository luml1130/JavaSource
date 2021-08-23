package com.luml.java.javaclass.Object;

import com.luml.domain.User;

/**
 * @author luml
 * @description
 * @date 2021/8/23 下午10:21
 */
public class ObjectTest {
    public static void main(String[] args) {
        User u1 = new  User();
        u1.setName("lu");
        u1.setMobile("1");
        User u2 = new  User();
        u2.setName("lu");
        u2.setMobile("1");

        System.out.println(u1.hashCode());//1670675563
        System.out.println(u2.hashCode());//723074861
        System.out.println(u1.equals(u2));//false
        System.out.println(u1.hashCode());//1670675563
        System.out.println(u2.hashCode());//723074861
        System.out.println(u1.equals(u2));//false


        System.out.println("======================");

        // 重写hashcode方法和 equals方法
        User u3 = new  User();
        u1.setName("lu");
        u1.setMobile("1");
        User u4= new  User();
        u2.setName("lu");
        u2.setMobile("1");
        System.out.println(u3.hashCode());//895328852
        System.out.println(u4.hashCode());//1304836502
        System.out.println(u3.equals(u4));//true

        System.out.println(u3.hashCode());//895328852
        System.out.println(u4.hashCode());//1304836502
        System.out.println(u3.equals(u4));//true
    }
    /**
     * 107464
     * 107464
     * true
     * 107464
     * 107464
     * true
     *
     * 0
     * 0
     * true
     * 0
     * 0
     * true
     */
}

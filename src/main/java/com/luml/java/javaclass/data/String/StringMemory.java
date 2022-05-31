package com.luml.java.javaclass.data.String;

import com.luml.domain.Person;

/**
 * @author luml
 * @description
 * @date 2022/5/30
 */
public class StringMemory {

    public static void main(String[] args) {
        String s1 = new String("myString");
        String s2 = "myString";
        String s3 = "myString";
        System.out.println(String.class.getName() + "@" + Integer.toHexString(System.identityHashCode(s1)));
        System.out.println(String.class.getName() + "@" + Integer.toHexString(System.identityHashCode(s2)));
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
    }

    public static void main2(String[] args) {
        //String a = new String("abc");
        Person p = new Person();
        p.setName("abc");
        String a = p.getName();
        System.out.println(String.class.getName() + "@" + Integer.toHexString(System.identityHashCode(a)));
        //java.lang.String@2626b418
        new Thread("线程1") {
            @Override
            public void run() {
                String b = p.getName();//和上面的一样 java.lang.String@2626b418
                //String b = new String("abc"); //不一样  java.lang.String@4e7cf7cb
                System.out.println(String.class.getName() + "@" + Integer.toHexString(System.identityHashCode(b)));
            }
        }.start();

    }

}

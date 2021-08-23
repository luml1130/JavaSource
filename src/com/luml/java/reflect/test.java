package com.luml.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author luml
 * @description
 * @date 2020/9/3
 */
public class test {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
       // Constructor  String .class.getConstructor(StringBuffer.class);
       Constructor constructor =  String.class.getConstructor(StringBuffer.class);
       String strt2  = (String) constructor.newInstance(new StringBuffer("abc"));
        System.out.println(strt2);
    }
}

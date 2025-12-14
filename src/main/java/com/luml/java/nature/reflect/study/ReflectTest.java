package com.luml.java.nature.reflect.study;

/**
 * @author luml
 * @description
 * @date 2021/11/17
 */
public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException {

        String  str1 = "abc";
        Class  cls1= str1.getClass();//1份字节码
        Class  cls2 = String.class;//2份字节码
        Class  cls3  = Class.forName("java.lang.String");//3份字节码
        //请问这三分字节码是不是同一份对象呢？
        System.out.println(cls1==cls2);//true
        System.out.println(cls1==cls3);//true

        //  isPrimitive是不是原始类型
        System.out.println(cls1.isPrimitive());//false
        System.out.println(int.class.isPrimitive());//基本数据类型。true
        System.out.println(int.class ==Integer.class);//false因为int和Integer各有各的字节码类。
        System.out.println(int .class==Integer.TYPE);//true;
        //数组的这种类型是不是原始类型呢？但是他不是原始类型。
        System.out.println(int [].class.isPrimitive());
        //判断一个class是不是数组时，该用相应的方法。isArray
        System.out.println(int [] .class.isArray());
    }
}

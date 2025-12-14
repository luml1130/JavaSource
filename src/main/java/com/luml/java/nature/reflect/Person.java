package com.luml.java.nature.reflect;

/**
 * @author luml
 * @description
 * @date 2021/11/17
 */
public class Person {

    public int price;
    private String name;

    public static void main(String[] args) {
        System.out.println("main......");
    }

    public String eat(String s){
        return "我爱吃"+s;
    }

    private String privateEat(String s){
        return "我就是爱吃"+s;
    }

    public static void say(){
        System.out.println("static.....");
    }
}

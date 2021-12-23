package com.luml.java.javaclass;

/**
 * @author luml
 * @description
 * @date 2020/4/22 16:07
 */
public class MyAbstract {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.jiao();
    }
}

abstract class Animals{
    public void say() {}
    public abstract void jiao();
    public abstract  void pao();
}

class Dog extends Animals{
    @Override
    public void jiao() {System.out.println("汪汪");}
    @Override
    public void pao() {System.out.println("撒丫子跑");}
}
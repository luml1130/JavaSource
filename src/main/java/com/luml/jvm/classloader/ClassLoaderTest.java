package com.luml.jvm.classloader;

/**
 * @author luml
 * @description
 * @date 2020/4/28 12:03
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent().toString());
        //sun.misc.Launcher$ExtClassLoader@24d46ca6
    }
}

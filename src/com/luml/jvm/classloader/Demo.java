package com.luml.jvm.classloader;

/**
 * @author luml
 * @description
 * JVM之java程序编译和运行的过程:https://blog.csdn.net/yelllowcong/article/details/77620403
 * @date 2020/4/28 12:12
 */
public class Demo {
    public static void main(String[] args) {
        //类加载器的设计，使用的是双亲委托模型
        //SystemClassLoader系统类加载器
        ClassLoader classLoader = Demo.class.getClassLoader();
        System.out.println(classLoader);
        //AppClassLoader和ExtensionClassLoader 两个都没有类加载器，就会找上一级的加载器
        System.out.println(classLoader.getClass().getClassLoader());

        //AppClassLoader加载的class
        System.out.println(System.getProperty("java.class.path"));

        //ExtensionClassLoader标准扩展加载器
        ClassLoader extClassLoader = classLoader.getParent();
        System.out.println(extClassLoader);
        System.out.println(extClassLoader.getClass().getClassLoader());
        System.out.println(System.getProperty("java.ext.dirs"));

        //引导类加载器，这个BootStrapClassLoader加载器使用的是C语言写的所以找不到
        ClassLoader bootStrapClassLoader = extClassLoader.getParent();
        System.out.println(bootStrapClassLoader);
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
    /**
     * sun.misc.Launcher$AppClassLoader@18b4aac2
     * null
     */
}

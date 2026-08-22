package com.luml.source.java.javaClass;

public class SystemTest {
    public static void main(String[] args) {
        //获取全部配置
        System.out.println(System.getenv());
        /**
         * 获取单个配置
         * /Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home
         */
        System.out.println(System.getenv("JAVA_7_HOME"));

    }
}

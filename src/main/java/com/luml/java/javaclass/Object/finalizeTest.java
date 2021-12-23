package com.luml.java.javaclass.Object;

import java.io.IOException;

/**
 * @author luml
 * @description
 * @date 2020/4/28 22:27
 */
public class finalizeTest {
    public static void main(String[] args) throws IOException {
        com.luml.java.javaclass.Object.M m = new com.luml.java.javaclass.Object.M();
        m = null;
        System.gc();
        //阻塞Main线程，给垃圾回收线程时间执行
        System.in.read();
    }
}

class M{
    //对象被回收时 调用finalize方法
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
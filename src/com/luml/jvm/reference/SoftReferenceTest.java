package com.luml.jvm.reference;

import java.lang.ref.SoftReference;

/**
 * @author luml
 * @description
 * @date 2020/4/28 22:35
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        //10M
        SoftReference<Byte[]> m = new SoftReference<>(new Byte[1024*1024*10]);
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //第一次没有回收
        System.out.println(m.get());
        /**
         * 再分配一个数组，heap堆装不下了，这时候系统会回收垃圾，
         * 先回收一次，如果不够会把软引用也回收了
         * 可以设置Xms为20M测试下不够后会不会回收m的软引用
         */
        Byte[] b = new Byte[1024*1024*15];
        System.out.println(m.get());
    }
}

package com.luml.java.javaclass.data.String;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author luml
 * @description
 * String和StringBuilder、StringBuffer的区别？
 * StringBuffer、StringBuilder扩容
 * @date 2026/5/20
 */
public class StringComparison {
    public static void main(String[] args) {
        // 1. String: 每次循环都创建新对象，性能最差
        String str = "";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            str += "a"; // 编译后实际是 new StringBuilder().append(str).append("a").toString()
        }
        System.out.println("String 耗时: " + (System.currentTimeMillis() - start) + " ms");

        // 2. StringBuilder: 单线程最快，无锁开销
        StringBuilder sb = new StringBuilder();
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            sb.append("a");
        }
        System.out.println("StringBuilder 耗时: " + (System.currentTimeMillis() - start) + " ms");

        // 3. StringBuffer: 线程安全，但比 StringBuilder 慢
        StringBuffer sbf = new StringBuffer();
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            sbf.append("a");
        }
        System.out.println("StringBuffer 耗时: " + (System.currentTimeMillis() - start) + " ms");

    }


    @Test
    public void lengthTest(){
        StringBuffer sb = new StringBuffer();
        sb.append("abcd");
        System.out.println(sb.length());//返回当前length  4
        System.out.println(sb.capacity());//初始化长度 默认16

        StringBuffer stringBuffer = new StringBuffer(10);
        stringBuffer.append("abcd");
        System.out.println(stringBuffer.length());//返回当前length 4
        System.out.println(stringBuffer.capacity());//10  不用扩容

        StringBuffer stringBuffer1 = new StringBuffer(10);
        stringBuffer1.append("abcd");
        System.out.println(stringBuffer1.length());//返回当前length 4
        System.out.println(stringBuffer1.capacity());//10  不用扩容
    }

    //扩容伪代码逻辑
    /*
    private void ensureCapacityInternal(int minimumCapacity) {
         // oldCapacity 是当前数组长度
        //  minimumCapacity 是至少需要的长度
        if (minimumCapacity - oldCapacity > 0) {
            int newCapacity = (oldCapacity << 1) + 2; // 等价于 oldCapacity * 2 + 2

            // 如果翻倍还不够，就直接扩展到刚好够用的大小
            if (newCapacity - minimumCapacity < 0) {
                newCapacity = minimumCapacity;
            }

            // 处理溢出情况
            if (newCapacity < 0 || newCapacity > MAX_ARRAY_SIZE) {
                hugeCapacity(minimumCapacity);
            }

            // 执行数组拷贝扩容
            Arrays.copyOf(value, newCapacity);
        }
    }*/

    @Test
    public void CapacityExpansionTest(){

        StringBuilder sb = new StringBuilder(); // 默认容量 16
        System.out.println("初始容量: " + sb.capacity()); // 输出 16
        // 追加 16 个字符，刚好填满，不扩容
        sb.append("0123456789ABCDEF");
        System.out.println("追加16字符后容量: " + sb.capacity()); // 输出 16
        System.out.println("当前长度: " + sb.length()); // 输出 16

        // 再追加 1 个字符，总需 17 > 16，触发扩容
        // 新容量 = 16 * 2 + 2 = 34
        sb.append("G");
        System.out.println("追加1字符后容量: " + sb.capacity()); // 输出 34

        // 再追加 34 个字符，总需 17+34=51 > 34，再次扩容
        // 新容量 = 34 * 2 + 2 = 70
        sb.append(new char[1]);
        System.out.println("再追加34字符后容量: " + sb.capacity()); // 输出 70
        /**
         * 初始容量: 16
         * 追加16字符后容量: 16
         * 当前长度: 16
         * 追加1字符后容量: 34
         * 再追加34字符后容量: 34
         */

    }

}

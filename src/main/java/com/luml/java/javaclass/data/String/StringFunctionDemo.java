package com.luml.java.javaclass.data.String;

import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2026/6/12
 */
public class StringFunctionDemo {

    /**
     * 统计子串出现次数
     */
    @Test
    public void countTest(){
        String str = "abracadabra";
        String target = "a";
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(target, index)) != -1) {
            count++;
            index += target.length(); // 移动索引，避免死循环
        }
        System.out.println("出现次数: " + count); // 输出: 5

    }
}

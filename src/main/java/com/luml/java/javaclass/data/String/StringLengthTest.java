package com.luml.java.javaclass.data.String;


import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2021/1/18 5:41 下午
 */
public class StringLengthTest {

    @Test
    public void lengthTest(){
        String aa = "woshi s  ";
        String bb = "你天内的是谁 ";
        System.out.println(aa.trim().length());
        System.out.println(bb.trim().length());
    }
}

package com.luml.juc.synchronizedTest;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author luml
 * @description
 * @date 2021/8/22 下午5:34
 */
public class test {

    public static void main(String[] args) {
        Object obj = new Object();
        obj.hashCode();  //显示调用下
        String classInfo = ClassLayout.parseInstance(obj).toPrintable();
        System.out.println(classInfo);
    }

}

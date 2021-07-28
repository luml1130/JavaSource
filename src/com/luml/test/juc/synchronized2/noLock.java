package com.luml.test.juc.synchronized2;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author luml
 * @description
 * @date 2021/7/28 下午10:42
 */
public class noLock {

    public static void main(String[] args) {
        Object model2 = new Object();
        System.out.println(ClassLayout.parseInstance(model2).toPrintable());
        System.out.println("hash: -----" + model2.hashCode());
        // 偏向锁
        System.out.println(ClassLayout.parseInstance(model2).toPrintable());
        System.out.println("hash: -----" + model2.hashCode());
        //binaryToDecimal(model2.hashCode());
    }

}

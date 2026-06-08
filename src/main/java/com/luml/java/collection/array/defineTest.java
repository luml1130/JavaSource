package com.luml.java.collection.array;

/**
 * @author luml
 * @description
 * 数组的定义
 * @date 2026/6/8
 */
public class defineTest {

    public static void main(String[] args) {
        // 动态初始化：创建一个长度为2的整型数组
        int[] scores = new int[3];

        // 此时数组中所有元素均为默认值 0
        System.out.println(scores); // 输出: 0
        System.out.println(scores); // 输出: 0

        // 后续可以通过索引逐个赋值
        scores[0] = 95;
        scores[1] = 87;

        // 遍历查看结果
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + " ");
        }
        // 输出: 95 87 0
    }
}

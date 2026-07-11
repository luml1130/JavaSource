package com.luml.jvm.oom;

/**
 * @author luml
 * @description
 * @date 2026/7/12
 */
public class IntegerTest {
    public static void main(String[] args) {
        int a = 2_147_483_647; // Integer.MAX_VALUE
        int b = 1;
        int result = a + b;
        System.out.println(result);
        // 输出: -2147483648 (即 Integer.MIN_VALUE)

    }
}

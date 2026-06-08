package com.luml.written;

import com.luml.juc.lock.keyword.volatileTest.Test;

/**
 * @author luml
 * @description
 * @date 2026/6/8
 */
public class panduanTest {

    public static void main(String[] args) {
        System.out.println(test());
    }

    /**
     * 以下代码输出什么？
     * 解析‌：
     *     return x++ 会先保存 x 的当前值（1）作为返回值，然后 x 自增为 2。
     *     finally 块一定会执行，所以打印 Finally: 10。
     *     但是，finally 中没有 return 语句，所以方法最终返回的是 try 块中保存的值（1），而不是 finally 中修改后的值。
     *     ‌答案‌：
     *     打印：Finally: 10
     *     返回：1
     * @return
     */
    public static int test() {
        int x = 1;
        try {
            return x++;
        } catch (Exception e) {
            return x;
        } finally {
            x = 10;
            System.out.println("Finally: " + x);
        }
    }

}

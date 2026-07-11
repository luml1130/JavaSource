package com.luml.threadOther.threadLocal;

/**
 * @author luml
 * @description
 * @date 2021/8/18 下午10:21
 */
public class ThreadLocalTest {

    private static final ThreadLocal<String> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        // 第一次设置
        tl.set("Hello");
        System.out.println(tl.get()); // 输出: Hello

        // 第二次设置（覆盖）
        tl.set("World");
        System.out.println(tl.get()); // 输出: World

        // 再次获取，依然是最后一次设置的值
        System.out.println(tl.get()); // 输出: World
    }
}

package com.luml.java.exception;

/**
 * @author luml
 * @description
 * 演示：如果 finally 中抛出异常，它会‌覆盖‌ try 或 catch 中原本要返回的值或抛出的异常。
 * 输出结果：
 * 捕获到的异常: java.lang.RuntimeException
 * 异常消息: Finally block failed!
 * @date 2026/7/20
 */
public class ExceptionCoveringDemo {
    public static void main(String[] args) {
        try {
            methodWithCoveredError();
        } catch (Exception e) {
            System.out.println("捕获到的异常: " + e.getClass().getName());
            System.out.println("异常消息: " + e.getMessage());
            // 注意：这里无法获取到原始的 NullPointerException
        }
    }

    public static void methodWithCoveredError() throws Exception {
        try {
            // 1. Try 块抛出原始异常 A (NullPointerException)
            String str = null;
            str.length();
        } finally {
            // 2. Finally 块抛出覆盖异常 B (RuntimeException)
            // 这通常发生在资源关闭失败时，例如 close() 抛出 IOException
            throw new RuntimeException("Finally block failed!");
        }
    }
}

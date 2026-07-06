package com.luml.java.nature.T_fanxing;

/**
 * @author luml
 * @description
 *  自定义泛型方法
 *  泛型方法独立于类的泛型存在，常用于工具类中处理不确定类型的逻辑。
 * @date 2026/7/6
 */
public class Utils {
    // 定义泛型方法：<T> 声明在返回值之前
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] intArr = {1, 2, 3};
        String[] strArr = {"A", "B", "C"};

        // 编译器自动推导 T 为 Integer
        printArray(intArr);
        // 编译器自动推导 T 为 String
        printArray(strArr);
    }
}

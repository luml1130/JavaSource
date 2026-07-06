package com.luml.java.nature.T_fanxing;

/**
 * @author luml
 * @description
 *  1. 自定义泛型类
 *   泛型类允许你在类定义时使用类型参数 T，在实例化时再指定具体类型。
 *  // 定义一个通用的盒子类，可以装任何类型的物品
 * @date 2026/7/6
 */
public class Box<T> {
    private T content;

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public static void main(String[] args) {
        // 装整数
        Box<Integer> integerBox = new Box<>();
        integerBox.setContent(100);
        Integer num = integerBox.getContent(); // 无需强转，直接获取 Integer

        // 装字符串
        Box<String> stringBox = new Box<>();
        stringBox.setContent("Hello Generics");
        String str = stringBox.getContent(); // 无需强转，直接获取 String

        // 编译期安全保护：以下代码会直接报错，防止运行时异常
        // integerBox.setContent("String");
    }
}

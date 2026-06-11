package com.luml.java.jdkNewFeature.jdk18.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author luml
 * @description
 * @date 2026/6/11
 */
public class CoreInterfacesDemo {
    /**
     * 四大核心基础接口
     * @param args
     */
    public static void main(String[] args) {
        // 1. Consumer: 消费型 (打印字符串)
        Consumer<String> print = s -> System.out.println("Hello, " + s);
        print.accept("World");

        // 2. Supplier: 供给型 (生成随机整数)
        Supplier<Integer> randomSup = () -> (int) (Math.random() * 100);
        System.out.println("Random: " + randomSup.get());

        // 3. Function: 函数型 (字符串转长度)
        Function<String, Integer> strLen = s -> s.length();
        System.out.println("Length: " + strLen.apply("Java"));

        // 4. Predicate: 断言型 (判断是否大于10)
        Predicate<Integer> isGreaterThan10 = n -> n > 10;
        System.out.println("Is > 10? " + isGreaterThan10.test(15));
    }
}

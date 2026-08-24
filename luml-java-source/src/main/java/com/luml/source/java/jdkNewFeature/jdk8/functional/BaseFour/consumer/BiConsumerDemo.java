package com.luml.source.java.jdkNewFeature.jdk8.functional.BaseFour.consumer;

import java.util.HashMap;
import java.util.Map;

public class BiConsumerDemo {
    public static void main(String[] args) {
        // 1. 基本用法：Lambda 表达式
        System.out.println("=== 1. 基本 Lambda 用法 ===");
        CustomBiConsumer<String, Integer> printInfo = (name, age) ->
                System.out.println("姓名: " + name + ", 年龄: " + age);

        printInfo.accept("张三", 25);
        printInfo.accept("李四", 30);

        // 2. 链式调用：andThen
        System.out.println("\n=== 2. andThen 链式调用 ===");
        CustomBiConsumer<String, Integer> logData = (name, age) ->
                System.out.println("[日志] 处理数据: " + name);

        CustomBiConsumer<String, Integer> combined = printInfo.andThen(logData);
        combined.accept("王五", 28);

        // 3. 实际场景：遍历 Map
        System.out.println("\n=== 3. 遍历 Map (模拟 Map.forEach) ===");
        Map<String, Double> scores = new HashMap<>();
        scores.put("数学", 95.5);
        scores.put("英语", 88.0);
        scores.put("语文", 92.0);

        CustomBiConsumer<String, Double> printScore = (subject, score) ->
                System.out.println(subject + ": " + score);

        // 模拟 forEach 行为
        for (Map.Entry<String, Double> entry : scores.entrySet()) {
            printScore.accept(entry.getKey(), entry.getValue());
        }

        // 4. 方法引用
        System.out.println("\n=== 4. 方法引用 ===");
        BiConsumerDemo demo = new BiConsumerDemo();
        CustomBiConsumer<String, String> sender = demo::sendMessage;
        sender.accept("管理员", "系统维护通知");
    }

    /**
     * 用于方法引用的示例方法
     */
    public void sendMessage(String from, String content) {
        System.out.println("收到来自 [" + from + "] 的消息: " + content);
    }
}

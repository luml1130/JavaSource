package com.luml.source.nature.reflect.demo;

/**
 * @author luml
 * @description
 * @date 2026/7/6
 */
public class User {
    // 公共字段
    public String name;
    // 私有字段
    private int age;
    // 静态字段
    private static String country = "China";

    // 公共无参构造
    public User() {
    }

    // 公共带参构造
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 私有构造
    private User(int age) {
        this.age = age;
    }

    // 公共方法
    public void sayHello() {
        System.out.println("Hello, my name is " + name);
    }

    // 私有方法
    private String getSecret() {
        return "My age is " + age;
    }

    // Getter/Setter
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

package com.luml.java.nature.reflect.domain;

/**
 * @author luml
 * @description
 * @date 2026/4/12
 */
public class ReflectFieldExample {
    private String name;
    private int age;
    private String email;

    public ReflectFieldExample() {}

    public ReflectFieldExample(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

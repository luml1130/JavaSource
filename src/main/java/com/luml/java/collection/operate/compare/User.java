package com.luml.java.collection.operate.compare;

/**
 * @author luml
 * @description
 * @date 2020/4/28 16:18
 */
public class User{
    private String name;

    private Integer age;

    private String mind;

    public User(String name, Integer age, String mind) {
        this.name = name;
        this.age = age;
        this.mind = mind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMind() {
        return mind;
    }

    public void setMind(String mind) {
        this.mind = mind;
    }
}

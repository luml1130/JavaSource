package com.luml.jvm.domain;

/**
 * @author luml
 * @description
 * @date 2021/8/14 下午9:44
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

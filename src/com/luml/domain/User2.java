package com.luml.domain;

/**
 * @author luml
 * @description
 * @date 2021/9/16 下午10:36
 */
//@Accessors(chain = true) // 链式方法
public class User2 {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

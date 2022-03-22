package com.luml.java.data;

import java.util.List;

/**
 * @author Andre_lml
 * @date 2019/3/2
 */
public class UserVo {
    private String name;
    private Integer age;
    private List<UserVo> friends;

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

    public List<UserVo> getFriends() {
        return friends;
    }

    public void setFriends(List<UserVo> friends) {
        this.friends = friends;
    }
}

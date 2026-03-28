package com.luml.domain;

import book.MultiThreadProgram.Part03.chapter01.interrupu05_10.waitOld.Add;
import lombok.Data;
import org.omg.CORBA.INTERNAL;

import java.util.Optional;

/**
 * @author luml
 * @description
 * @date 2021/9/16 下午10:36
 */
//@Accessors(chain = true) // 链式方法
public class User2 {
    private Integer id;
    private String name;
    private Integer age;
    //private Address address;
    private Optional<Address> address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Optional<Address> getAddress() {
        return address;
    }

    public void setAddress(Optional<Address> address) {
        this.address = address;
    }

    public User2(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User2(String name, Optional<Address> address) {
        this.name = name;
        this.address = address;
    }
}

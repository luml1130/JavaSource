package com.luml.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2020/4/30 21:08
 */
public class Person2 {
    private String name;
    private String nickName;
    private int gender; //0-男  1-女
    private int salary; //薪水

    private BigDecimal amount;

    public Person2() {
    }

    public Person2(String name,  int gender, BigDecimal amount) {
        this.name = name;
        this.gender = gender;
        this.amount = amount;
    }

    public Person2(String name, String nickName, int gender, int salary) {
        this.name = name;
        this.nickName = nickName;
        this.gender = gender;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

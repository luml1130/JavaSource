package com.luml.domain.sort;

import lombok.Data;

import java.util.Date;

/**
 * @author luml
 * @description
 * @date 2025/12/17
 */
@Data
public class Student implements Comparable<Student>{

    String name;
    int age;
    Date birthDay;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, Date birthDay) {
        this.name = name;
        this.age = age;
        this.birthDay = birthDay;
    }

    @Override
    public int compareTo(Student other) {
        return this.age - other.age; // 升序：年龄小的在前
        //如果需要降序，可以返回 other.age - this.age。
    }
}

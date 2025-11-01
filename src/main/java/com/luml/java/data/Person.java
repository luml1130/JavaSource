package com.luml.java.data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author luml
 * @description
 * @date 2022/3/22
 */
@Data
public class Person {

    @JSONField(name = "AGE",serialize = false)
    private int id;

    @JSONField(name = "AGE")
    private int age;

    @JSONField(name = "FULL NAME")
    private String fullName;

    @JSONField(name = "DATE OF BIRTH")
    private Date dateOfBirth;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date receiptTime;


    public Person(int id, int age, String fullName, Date dateOfBirth, Date receiptTime) {
        this.id = id;
        this.age = age;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.receiptTime = receiptTime;
    }
}

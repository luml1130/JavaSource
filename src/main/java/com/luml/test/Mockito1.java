package com.luml.test;

public class Mockito1 {

    private Database database;

    public Mockito1(Database database) {
        this.database = database;
    }

    public int add(int a, int b) {
        int data = database.getData(); // 从数据库获取数据
        return a + b + data; // 返回结果
    }
}

package com.luml.java.collection.map.hashTable.Demo;

/**
 * @author luml
 * @description 雇员类
 * @date 2020/5/9 19:06
 */
public class Emp {
    public int id;
    public String name;
    public Emp next = null;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }
}

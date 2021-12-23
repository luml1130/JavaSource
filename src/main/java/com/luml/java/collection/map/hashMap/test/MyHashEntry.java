package com.luml.java.collection.map.hashMap.test;

/**
 * @author luml
 * @description
 * @date 2021/7/17 上午11:11
 */
public class MyHashEntry {

    private final Object key;
    private Object value;
    private MyHashEntry next;

    public MyHashEntry(Object key, Object value, MyHashEntry next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public MyHashEntry getNext() {
        return next;
    }

    public void setNext(MyHashEntry next) {
        this.next = next;
    }
}

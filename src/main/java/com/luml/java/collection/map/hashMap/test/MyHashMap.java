package com.luml.java.collection.map.hashMap.test;

/**
 * @author luml
 * @description
 * HashMap的简化实现MyHashMap
 * 为了加深理解，我个人实现了一个简化版本的HashMap，注意哦，仅仅是简化版的功能并不完善，仅供参考
 * @date 2021/7/17 上午11:09
 */
public class MyHashMap {

    //默认初始化大小 16
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    //默认负载因子 0.75
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    //临界值
    private int threshold;
    //元素个数
    private int size;
    //扩容次数
    private int resize;
    private MyHashEntry[] table;

    public MyHashMap() {
        table = new MyHashEntry[DEFAULT_INITIAL_CAPACITY];
        threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        size = 0;
    }

    private int index(Object key) {
        //根据key的hashcode和table长度取模计算key在table中的位置
        return key.hashCode() % table.length;
    }

    /**
     * 增加方法
     * @param index
     * @param key
     * @param value
     */
    private void add(int index, Object key, Object value) {
        //将新的entry放到table的index位置第一个，若原来有值则以链表形式存放
        MyHashEntry entry = new MyHashEntry(key, value, table[index]);
        table[index] = entry;
        //判断size是否达到临界值，若已达到则进行扩容，将table的capacicy翻倍
        if (size++ >= threshold) {
            resize(table.length * 2);
        }
    }

    /**
     * put 方法
     * @param key
     * @param value
     */
    public void put(Object key, Object value) {
        //key为null时需要特殊处理，为简化实现忽略null值
        if (key == null) {
            return;
        }
        int index = index(key);
        //遍历index位置的entry，若找到重复key则更新对应entry的值，然后返回
        MyHashEntry entry = table[index];
        while (entry != null) {
            if (entry.getKey().hashCode() == key.hashCode() && (entry.getKey() == key || entry.getKey().equals(key))) {
                entry.setValue(value);
                return;
            }
            entry = entry.getNext();
        }
        //若index位置没有entry或者未找到重复的key，则将新key添加到table的index位置
        add(index, key, value);
    }

    /**
     * get方法
     * @param key
     * @return
     */
    public Object get(Object key) {
        //这里简化处理，忽略null值
        if (key == null) {
            return null;
        }
        MyHashEntry entry = getEntry(key);
        return entry == null ? null : entry.getValue();
    }

    /**
     * 移除方法
     * @param key
     */
    public void remove(Object key) {
        if (key == null) {
            return;
        }
        int index = index(key);
        MyHashEntry pre = null;
        MyHashEntry entry = table[index];
        while (entry != null) {
            if (entry.getKey().hashCode() == key.hashCode() && (entry.getKey() == key || entry.getKey().equals(key))) {
                if (pre == null) {
                    table[index] = entry.getNext();
                }else {
                    pre.setNext(entry.getNext());
                }
                //如果成功找到并删除，修改size
                size--;
                return;
            }
            pre = entry;
            entry = entry.getNext();
        }
    }


    public MyHashEntry getEntry(Object key) {
        MyHashEntry entry = table[index(key)];
        while (entry != null) {
            if (entry.getKey().hashCode() == key.hashCode() && (entry.getKey() == key || entry.getKey().equals(key))) {
                return entry;
            }
            entry = entry.getNext();
        }
        return null;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        this.size = 0;
    }

    public boolean containsKey(Object key) {
        if (key == null) return false;
        return getEntry(key) != null;
    }

    private void resize(int capacity) {
        if (capacity <= table.length) {
            return;
        }
        MyHashEntry[] newTable = new MyHashEntry[capacity];
        //遍历原table，将每个entry都重新计算hash放入newTable中
        for (int i = 0; i < table.length; i++) {
            MyHashEntry old = table[i];
            while (old != null) {
                MyHashEntry next = old.getNext();
                int index = index(old.getKey());
                old.setNext(newTable[index]);
                newTable[index] = old;
                old = next;
            }
        }
        //用newTable替table
        table = newTable;
        //修改临界值
        threshold = (int) (table.length * DEFAULT_LOAD_FACTOR);
        resize++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("size:%s capacity:%s resize:%s\n\n", size, table.length, resize));
        for (MyHashEntry entry : table) {
            while (entry != null) {
                sb.append(entry.getKey() + ":" + entry.getValue() + "\n");
                entry = entry.getNext();
            }
        }
        return sb.toString();
    }

}

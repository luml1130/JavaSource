package com.luml.java.collection.Set;

import java.util.LinkedHashSet;

/**
 * @author Andre_lml
 * LinkedHashSet:底层数据结构由哈希表和链表组成
 * 哈希表保证元素的唯一性。
 * 链表保证元素有素。(存储和取出是一致)
 * @date 2019/4/23
 */
public class LinkedHashSetDemo {
    public static void main(String[] args) {
        //Set<String> set = new LinkedHashSet<>();
       // Set<String> set = new LinkedHashSet<String>();
        // 创建集合对象
        LinkedHashSet<String> hs = new LinkedHashSet<String>();
        // 创建并添加元素
        hs.add("hello");
        hs.add("world");
        hs.add("java");
        hs.add("world");
        hs.add("java");

        System.out.println(hs.contains("hello"));
        // 遍历
        for (String s : hs) {
            System.out.println(s);
        }
    }

}

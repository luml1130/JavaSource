package com.luml.java.collection.list.synchronizedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2026/5/20
 */
public class SynchronizedListTest {
    public static void main(String[] args) {
        List<String> lists = new ArrayList<String>();// 创建一个List数组
        // 添加元素
        lists.add("1");
        lists.add("2");
        lists.add("3");
        // 创建一个synchronizedList
        List<String> synlist = Collections.synchronizedList(lists);
        // 迭代集合元素
        synchronized (lists) {
            Iterator<String> iterator = synlist.iterator();//获取迭代器
            while (iterator.hasNext()) {//遍历
                System.out.println(iterator.next());
            }
        }
    }
}

package com.luml.java.collection.tree.RedBlackTree;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author luml
 * @description
 * @date 2026/6/14
 */
public class RedBlackTreeDemo {
    public static void main(String[] args) {
        // TreeMap 底层是红黑树
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);

        // 自动按键排序输出: Apple, Banana, Cherry
        System.out.println(map.keySet());

        // TreeSet 底层也是红黑树
        TreeSet<Integer> set = new TreeSet<>();
        set.add(5);
        set.add(1);
        set.add(3);

        // 自动排序输出: 1, 3, 5
        System.out.println(set);
    }
}

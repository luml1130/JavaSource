package com.luml.java.collection.operate.transf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author luml
 * @description
 * @date 2026/2/11
 */
public class SetToListConverter {

    public static void main(String[] args) {
        // 示例：String类型的Set转List
        Set<String> stringSet = new HashSet<>();
        stringSet.add("apple");
        stringSet.add("banana");
        stringSet.add("orange");

        List<String> stringList = setToList(stringSet);
        System.out.println("String Set转List: " + stringList);

        // 示例：Integer类型的Set转List
        Set<Integer> intSet = new HashSet<>();
        intSet.add(1);
        intSet.add(2);
        intSet.add(3);

        List<Integer> intList = setToList(intSet);
        System.out.println("Integer Set转List: " + intList);
    }


    /**
     * 将Set转换为List
     * @param set 输入的Set集合
     * @param <T> 泛型参数
     * @return 转换后的List集合
     */
    public static <T> List<T> setToList(Set<T> set) {
        if (set == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(set);
    }

    /**
     * 将Set转换为List并保持指定顺序
     * @param set 输入的Set集合
     * @param order 指定的List类型
     * @param <T> 泛型参数
     * @return 转换后的List集合
     */
    public static <T> List<T> setToList(Set<T> set, Class<? extends List> order) {
        if (set == null) {
            return new ArrayList<>();
        }

        if (order == ArrayList.class) {
            return new ArrayList<>(set);
        } else if (order == LinkedList.class) {
            return new LinkedList<>(set);
        } else {
            return new ArrayList<>(set);
        }
    }


}

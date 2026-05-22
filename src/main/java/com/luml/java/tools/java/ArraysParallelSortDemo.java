package com.luml.java.tools.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author luml
 * @description
 * @date 2026/5/22
 */
class Person {
    String name;
    int age;
    Person(String name, int age) { this.name = name; this.age = age; }
    public String toString() { return name + ":" + age; }
}
public class ArraysParallelSortDemo {

    /**
     * 基本类型数组排序
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[2];
        // 填充随机数据
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000000);
        }

        // 并行排序整个数组
        Arrays.parallelSort(arr);

        // 并行排序指定范围 [fromIndex, toIndex)
        // Arrays.parallelSort(arr, 0, 500000);
    }

    /**
     * 对象数组排序（自定义 Comparator）
     */
    @Test
    public void ObjectParallelSort(){

        Person[] people = new Person[2];
        // 初始化数据...

        // 按年龄并行排序
        Arrays.parallelSort(people, Comparator.comparingInt(p -> p.age));

    }
}

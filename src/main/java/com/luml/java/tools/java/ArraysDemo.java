package com.luml.java.tools.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2026/5/12
 */
public class ArraysDemo {

    @Test
    public void toStringTest(){
        int[] arr = {1, 2, 3};
        System.out.println(Arrays.toString(arr)); // 输出: [1, 2, 3]

        int[][] matrix = {{1, 2}, {3, 4}};
        System.out.println(Arrays.deepToString(matrix)); // 输出: [[1, 2], [3, 4]]
    }

    @Test
    public void sortTest(){
        int[] nums = {5, 3, 8, 1};
        Arrays.sort(nums); // 结果: [1, 3, 5, 8]

        String[] strs = {"banana", "apple", "cherry"};
        Arrays.sort(strs, Collections.reverseOrder()); // 降序排列



    }

    /**
     * 数组查找（二分搜索）
     */
    @Test
    public void binarySearchTest(){
        int[] sortedArr = {1, 3, 5, 7, 9};
        int index = Arrays.binarySearch(sortedArr, 5); // 返回 2
        int notFound = Arrays.binarySearch(sortedArr, 6); // 返回负数

    }

    @Test
    public void equalsTest(){

        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        System.out.println(Arrays.equals(a, b)); // true
        System.out.println(a == b); // false (地址不同)

    }

    @Test
    public void fillTest(){
        int[] arr = new int[5];
        Arrays.fill(arr, 10); // [10, 10, 10, 10, 10]
        Arrays.fill(arr, 1, 3, 99); // [10, 99, 99, 10, 10]

    }

    @Test
    public void copyOfTest(){
        int[] original = {1, 2, 3, 4, 5};
        int[] copy = Arrays.copyOf(original, 3); // [1, 2, 3]
        int[] extended = Arrays.copyOf(original, 7); // [1, 2, 3, 4, 5, 0, 0]

    }

    @Test
    public void asListTest(){
        String[] arr = {"A", "B", "C"};
        List<String> list = Arrays.asList(arr);
        // list.add("D"); // 抛出异常
        list.set(0, "Z"); // 合法，arr 也会变为 "Z"

    }

}

package com.luml.java.collection.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author luml
 * @description
 * @date 2026/4/23
 */
public class ArraysTest {

    @Test
    public void toStringTest(){
        //Arrays.toString
    }

    @Test
    public void sortTest(){
        //Arrays.sort
    }

    /**
     * Arrays.parallelSort 是处理大规模数据排序的有力工具，
     * 但在小数据量或单核环境下应谨慎使用，优先选择传统的 Arrays.sort。
     */
    @Test
    public void ParallelSortTest(){
        //1、基本类型数组排序
        int[] numbers = {5, 3, 6, 1, 9, 4};
        Arrays.parallelSort(numbers); // 并行排序
        System.out.println(Arrays.toString(numbers)); // 输出: [1, 3, 4, 5, 6, 9]

        //2、指定范围排序
        int[] arr = {5, 3, 8, 1, 9, 4, 2, 7};
        // 仅对索引 2 到 6（不包含6）的元素进行排序
        Arrays.parallelSort(arr, 2, 6);
        System.out.println(Arrays.toString(arr));
        // 前两个元素不变，中间四个元素排序，最后两个元素不变


       //3、对象数组排序（使用 Comparator）
        String[] words = {"banana", "apple", "pear", "orange"};
        // 使用自定义 Comparator 进行并行排序
        Arrays.parallelSort(words, Comparator.naturalOrder());//降序排序
        System.out.println(Arrays.toString(words)); // 输出: [apple, banana, orange, pear]

        String[] words2 = {"banana", "apple", "pear", "orange"};
        Arrays.parallelSort(words2, Comparator.reverseOrder());//升序排序
        System.out.println(Arrays.toString(words2)); // 输出: [pear, orange, banana, apple]


    }
}

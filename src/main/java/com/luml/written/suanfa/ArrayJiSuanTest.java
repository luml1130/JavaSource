package com.luml.written.suanfa;

import org.junit.Test;

import javax.xml.transform.Source;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luml
 * @description
 *  //二、 字符串与数组算法
 * @date 2026/6/8
 */
public class ArrayJiSuanTest {

    public static void main(String[] args) {
       // System.out.println(firstNonRepeatingChar("lumenglu"));

       // mergeSortedArrays();
    }

    //1. 查找字符串中第一个不重复的字符
    //思路‌：使用 HashMap 或数组统计字符出现次数，然后再次遍历字符串找到第一个计数为 1 的字符。
    //@Test
    public static char firstNonRepeatingChar(String str) {
        if (str == null || str.isEmpty()) return '\0';
        Map<Character, Integer> map = new HashMap<>();

        // 第一次遍历：统计频率
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // 第二次遍历：查找第一个唯一字符
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == 1) {
                return str.charAt(i);
            }
        }
        return '\0';
    }


    /**
     * 合并两个有序数组
     *
     * 给定两个按非递减顺序排列的整数数组 nums1 和 nums2，
     *      以及两个整数 m 和 n，分别表示 nums1 和 nums2 中的有效元素数目。
     * 要求将 nums2 合并到 nums1 中，使合并后的数组同样按非递减顺序排列，且结果直接存储在 nums1 中。
     *
     * ‌思路‌：双指针法。从两个数组的末尾开始比较，将较大的元素放入结果数组的末尾，避免覆盖未处理的元素（如果是原地合并）
     */
    //TODO 报错待整理
   // public void mergeSortedArrays(int[] nums1, int m, int[] nums2, int n) {
    @Test
    public void mergeSortedArrays() {
        int[] nums1 = {1,3,5,7,0,0,0,0,0};;
        int m =4;
        int[] nums2 ={2,4,6,8,10};
        int n =5;


        int p1 = m - 1; //p1 指向 nums1 有效元素的末尾
        int p2 = n - 1; //p2 指向 nums2 的末尾
        int p = m + n - 1; //p 指向 nums1 整个数组的末尾（即合并后存放位置）

        // 当两个数组都还有元素未处理时
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p--] = nums1[p1--];
            } else {
                System.out.println(nums1[p--]);
                System.out.println(nums2[p2--]);
                nums1[p--] = nums2[p2--];
            }
        }

        // 如果 nums2 还有剩余元素，直接复制到 nums1 前面
        // 注意：如果 nums1 有剩余，不需要处理，因为它们已经在正确的位置上
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }

        System.out.println(nums1);
    }


    /**
     * 最长无重复字符子串
     * 思路‌：滑动窗口 + HashMap。记录每个字符最后出现的位置，当遇到重复字符时，移动左边界。
     */
    @Test
    public void lengthOfLongestSubstring() {
        String s = "lumenglianglu"; //重复的有lu ng

        if (s == null || s.length() == 0) {
            //return 0;
            System.out.println("0");
        }
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            System.out.println("c="+c);
            if (map.containsKey(c)) {
                // 更新左边界，确保不回退
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        //return maxLen;
        System.out.println("maxLen="+maxLen);
        //maxLen=8  umenglia

    }

}

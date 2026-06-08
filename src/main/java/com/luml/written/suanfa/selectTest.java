package com.luml.written.suanfa;

import org.junit.Test;

import javax.sound.midi.Soundbank;

/**
 * @author luml
 * @description
 * 几个查询算法
 * @date 2026/6/8
 */
public class selectTest {

    /**
     * 有序数组 二分查找
     *  数组长度是11,查询出15的角标
     *
     *
     */
    @Test
    public void secondSort(){
    //public static int getIndex(int[] arr,int key) {
        int[] arr={2,11,15,19,30,32,61,72,88,90,96};
        int key = 15;


        int min = 0;
        int max = arr.length-1;
        int mid = (min + max)/2;
        while (key != arr[mid]){
            if (key > arr[mid]){
                min = mid + 1;
            }else if (key < arr[mid]){
                max = mid - 1;
            }
            if (min > max){
                System.out.println(-1);
               // return -1;
            }
            mid = (min + max)/2;
        }
       // return mid;
        System.out.println(mid);
    }

    /**
     * 题目‌：在一个升序排列整型数组 nums 中搜索目标值 target，如果存在返回下标，否则返回 -1。
     *      *  ‌思路‌：维护左右指针，每次缩小一半搜索范围。注意防止整数溢出。
     */
    @Test
    //TODO 好像错了
    public void test(){
    //public int binarySearch(int[] nums, int target) {

        int[] nums={2,11,15,19,30,32,61,72,88,90,96};
        int target = 15;

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // 防止 (left + right) 溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                //return mid;
                System.out.println(mid);
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        //return -1;
        System.out.println(-1);

    }
}

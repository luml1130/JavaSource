package com.luml.java.collection.array;

import java.util.Arrays;

/**
 * @author luml
 * @description
 * @date 2020/4/28 18:02
 */
public class sortTest {
    public static void main(String[] args) {
        int[] arr = {12,23,11,9,3,6,20,1};
        //测试排序性能
       /* Random r = new Random();
        int[] arr = new int[10000];
        for(int i=0;i<arr.length;i++){
            arr[i] = r.nextInt(arr.length);
        }*/
        System.out.println("排序前："+ Arrays.toString(arr));

        /**
         * Arrays.SORT  3
         */
        long start = System.currentTimeMillis();
        Arrays.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("API排序使用时间："+(end-start));
        System.out.println("排序后："+Arrays.toString(arr));

        /**
         * 选择排序 146
         */
        long start1 = System.currentTimeMillis();
        selectSort(arr);
        long end1 = System.currentTimeMillis();
        System.out.println("选择排序算法排序使用时间："+(end1-start1));
        System.out.println("排序后："+Arrays.toString(arr));
        /**
         * 冒泡排序 113
         */
        long start2 = System.currentTimeMillis();
        bubbleSort(arr);
        long end2 = System.currentTimeMillis();
        System.out.println("冒泡排序算法排序使用时间："+(end2-start2));
        System.out.println("排序后："+Arrays.toString(arr));
    }


    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[]arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                //swap(arr,i,j);
                if(arr[i]>arr[j]){
                    int temp=0;
                    temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
    }
    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length-i-1; j++){
                if(arr[j]>arr[j+1]){
                    int temp = 0;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
           /* for (int j = 0; j < arr.length-i-1; j++) {
                //swap(arr,j,j+1);
                if(arr[j]>arr[j+1]){
                    int temp=00;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }*/
        }
    }
    /**
     * 公共方法抽取:
     *  我们直接写的性能特别低，开发里面我们直接用java已经写好的排序方法
     * @param arr
     * @param index1
     * @param index2
     */
    private static void swap(int[] arr, int index1,int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}

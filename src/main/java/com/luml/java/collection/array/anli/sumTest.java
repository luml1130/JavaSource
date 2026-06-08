package com.luml.java.collection.array.anli;

/**
 * @author luml
 * @description
 * @date 2026/6/8
 */
public class sumTest {

    /**
     * 二维数组的应用-求一年的销售总额
     * @param args
     */
    public static void main(String[] args) {
        int[][] arr = {{1,100},{2,200},{3,800}};//1100
        printArray2(arr);
    }

    /*public static void printArray2(int[][] arr2){
        int sum = 0;
        for(int i=0; i<arr2.length; i++){
            int j=arr2[i].length-1;
            sum = sum + arr2[i][j];
        }
        System.out.println("sum =" + sum);
    }*/
    //如果要获得所有的二维数组的和
    public static void printArray2(int[][] arr2){
        int sum = 0;
        for(int i=0; i<arr2.length; i++){
            for(int j=0; j<arr2[i].length; j++){
                sum = sum + arr2[i][j];
            }
        }
        System.out.println("sum =" + sum);
        //sum =1106
    }

}

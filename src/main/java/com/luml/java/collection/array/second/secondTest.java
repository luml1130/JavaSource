package com.luml.java.collection.array.second;

/**
 * @author luml
 * @description
 * @date 2026/6/8
 */
public class secondTest {
    /**
     * 二维数组的遍历
     * @param args
     */
    public static void main(String[] args) {
        int[][] arr = {{3,8,2},{2,7},{9,0,1,6}};
        printArray2(arr);
    }
    public static void printArray2(int[][] arr2){
        for(int i=0; i<arr2.length; i++){
            for(int j=0; j<arr2[i].length; j++){
                System.out.print(arr2[i][j]+" ");
            }
            System.out.println();
        }
    }
}

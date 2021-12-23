package com.luml.java.operate;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author luml
 * @description
 * @date 2021/8/5 下午11:35
 */
public class arrPrint {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        //1、传统的for循环方式
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
        //2、for each循环
        for(int a:array) {
            System.out.println(a);
        }
        //3、利用Array类中的toString方法
        System.out.println(Arrays.toString(array));
    }

    /**
     * Java实际没有多维数组，只有一维数组，多维数组被解读为"数组的数组"，
     * 例如二维数组magicSquare是包含{magicSquare[0]，magicSquare[1]，magicSquare[2]}三个元素的一维数组，
     * magicSqure[0]是包含{16,3,2,13}四个元素的一维数组，同理magicSquare[1]，magicSquare[2]也一样。
     */
    @Test
    public void Array2Print(){
        int[][]magicSquare =
                {
                        {16,3,2,13},
                        {5,10,11,8},
                        {9,6,7,3}
                };
        //1、传统的for循环方式
        for(int i=0;i<magicSquare.length;i++){
            for(int j=0;j<magicSquare[i].length;j++){
                System.out.print(magicSquare[i][j]+" ");
            }
            System.out.println();	//换行
        }
        //2、for each循环
        for(int[] a:magicSquare){
            for(int b:a){
                System.out.print(b+" ");
            }
            System.out.println();//换行
        }
        //3、利用Array类中的toString方法
        for(int i=0;i<magicSquare.length;i++){
            System.out.println(Arrays.toString(magicSquare[i]));
        }
    }
}

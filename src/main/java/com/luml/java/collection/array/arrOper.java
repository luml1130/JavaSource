package com.luml.java.collection.array;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2020/11/23
 */
public class arrOper {

    public static void main(String[] args) {
        String[][] eval = new String[1][2];
        eval[0][0] = "1";
        //eval[0][1] = "2";

        System.out.println(eval[0][0]);
        System.out.println(eval[0][1]);

        String[][] eval2 = intakeRangeDic("energy");
        String format = String.format("（%s%%~%s%%）", eval2[0][0], eval2[0][1]);
        System.out.println(format);
    }

    @Test
    public void Array2Print1() {
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

    @Test
    public void operNull(){
        Integer[] times = {null};

        Integer shipmentStartTime = 111;
        Integer shipmentEndTime = 222;
        if(times!=null && times.length > 0) {
            shipmentStartTime = times[0] == null ?   shipmentStartTime : times[0] ;
            if(times.length > 1) {
                shipmentEndTime = times[1] == null ? shipmentEndTime : times[1];
            }
        }
        System.out.println(shipmentStartTime);
        System.out.println(shipmentEndTime);
    }

    @Test
    public void nullTest(){
        //param.getCreatedTime() 类型 Date[]
        /*if (Objects.isNull(param.getCreatedTime()) || param.getCreatedTime().length != INTEGER_TWO) {
            throw new E6ArgumentException("历史数据查询必须输入查询时间");
        }*/
    }


    public static String[][] intakeRangeDic(String nutrientId){
        String[][] rangeStr = new String[1][2];
        switch (nutrientId){
            case "energy":
                rangeStr[0][0] = "85";
                rangeStr[0][1] = "200";
                break;
            case "protein":
            case "fat":
            case "carbohydrate":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "200";
                break;
            case "va":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "340";
                break;
            case "vb1":
            case "vb2":
            case "vb3":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "5700";
                break;
            case "vc":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "1650";
                break;
            case "ve":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "5620";
                break;
            case "ca":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "250";
                break;
            case "fe":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "300";
                break;
            case "zn":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "230";
                break;
            case "se":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "740";
                break;
        }
        return rangeStr;
    }

    @Test
    public void arrayToList(){
        String str1 = "apple,banana,orange,grape";
        String[] array1 = str1.split(",");
        List<String> list1 = Arrays.asList(array1);
        System.out.println(list1);
    }
}

package com.luml.java.collection.list.arraylist1;

import java.util.Arrays;
import java.util.Date;

/**
 * @author luml
 * @description
 * @date 2026/3/12
 */
public class ArrayUtils {

    // 方法1: 使用System.arraycopy
    public static int[] addElementByCopy(int[] original, int element) {
        int[] newArray = new int[original.length + 1];
        System.arraycopy(original, 0, newArray, 0, original.length);
        newArray[original.length] = element;
        return newArray;
    }

    public static Date[] addElementByCopy(Date[] original, Date element) {
        Date[] newArray = new Date[original.length + 1];
        System.arraycopy(original, 0, newArray, 0, original.length);
        newArray[original.length] = element;
        return newArray;
    }

    // 方法2: 使用Arrays.copyOf
    public static int[] addElementByCopyOf(int[] original, int element) {
        int[] newArray = Arrays.copyOf(original, original.length + 1);
        newArray[original.length] = element;
        return newArray;
    }
}

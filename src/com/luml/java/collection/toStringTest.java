package com.luml.java.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2021/1/8 3:50 下午
 */
public class toStringTest {
    public static void main(String[] args) {
        list2String();
    }

    public static void list2String(){
        List<Integer> aa = new ArrayList<>();
        aa.add(11);
        aa.add(22);
        //[11, 22]
        System.out.println(Arrays.toString(aa.toArray()));
    }
}

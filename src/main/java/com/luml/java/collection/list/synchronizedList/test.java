package com.luml.java.collection.list.synchronizedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2021/8/22 下午10:05
 */
public class test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(10);
        Collections.synchronizedList(list);
    }
}

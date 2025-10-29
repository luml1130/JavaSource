package com.luml.java.tools;

import java.util.Collections;
import java.util.Set;

/**
 * @author luml
 * @description
 *  1、ava.util.Collections
 * @date 2025/10/29
 */
public class CollectionsUtilTest01 {
    public static void main(String[] args) {
        //java.util.Collections 构建只有一个元素的list
       Set<String> aaList = Collections.singleton("10");
        System.out.println(aaList);
    }
}

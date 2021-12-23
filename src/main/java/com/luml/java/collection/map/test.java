package com.luml.java.collection.map;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luml
 * @description
 * @date 2021/7/27 下午4:57
 */
public class test {

    public static void main(String[] args) {
        Map<String,String> a = new HashMap<>();
        a.put("A",null);
        System.out.println(a.get("A"));
        if(StringUtils.isBlank(a.get("B"))) {
            System.out.println("kong");
        }
    }
}

package com.luml.java.jdk18.api.stream;

import com.luml.domain.Person2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2021/9/18 下午3:38
 */
public class test {
    public static void main(String[] args) {
        System.out.println(Math.max(Math.toIntExact(5300), 0));
    }
    public static void main2(String[] args) {
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,40));
            add(new Person2("三木","sanmu",0,50));
        }};


    }
}

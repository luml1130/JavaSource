package com.luml.java.jdk18.api.stream;

import com.luml.domain.Person2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2025/11/19
 */
public class StreamGroupTest01 {

    public static void main(String[] args) {
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
        }};

        Map<Integer, List<Person2>> map1 = list.stream()
                .collect(Collectors.groupingBy(Person2::getGender));

        for (Map.Entry<Integer, List<Person2>> entry : map1.entrySet() ) {
            System.out.println( entry.getKey());
            System.out.println(entry.getValue());
        }

    }
}

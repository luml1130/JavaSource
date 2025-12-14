package com.luml.java.collection.operate;

import com.luml.domain.Person2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author luml
 * @description
 * @date 2025/12/14
 */
public class emptyTest {

    /**
     * Collections.unmodifiableXXX()方法，其中XXX是集合类型（如List、Set、Map等）。
     *          这种方法不会实际创建一个新的集合实例，而是返回一个原始集合的不可修改视图。
     *          对返回的集合进行修改操作会抛出UnsupportedOperationException：
     * Collections.emptyXXX()方法创建的集合实际上是空的，并且是不可变的。
     * @param args
     */
    public static void main(String[] args) {
        //创建空List
        List<String> emptyList = new ArrayList<>();
        List<String> emptyList2 = Collections.emptyList();

        //创建空Set
        Set<String> emptySet = new HashSet<>();
        Set<String> emptySet2 = Collections.emptySet();

        //创建空Map
        Map<String, String> emptyMap2 = new HashMap<>();
        Map<String, String> emptyMap = Collections.emptyMap();

        //创建不可修改的集合（不可修改的视图）

        List<String> modifiableList = new ArrayList<>(); // 原始可修改列表
        // 不可修改视图列表，不实际创建新实例，只是视图。
        List<String> unmodifiableList = Collections.unmodifiableList(modifiableList);
    }


    @Test
    public void testException(){
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
        }};

        Map<Integer, Person2> alarmStatPOMap = Collections.emptyMap();
        list.stream().forEach(person -> {
            alarmStatPOMap.put(person.getGender(), person);
        });
        //java.lang.UnsupportedOperationException
    }
}

package com.luml.java.jdkNewFeature.jdk19;

import com.google.common.collect.ImmutableList;
import com.luml.domain.Person;
import com.sun.tools.javac.util.List;

/**
 * @author luml
 * @description
 * @date 2025/12/6
 */
public class ListOfTest {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Alice", 1),
                new Person("Bob", 2),
                new Person("Charlie", 3)
        );
        //List<String> emptyList = List.of(); // 创建空列表  1.9不报错
        List<String> singleElement = List.of("apple");// 创建单元素列表
        List<String> multiElements = List.of("apple", "banana", "cherry");// 创建多元素列表
    }
    public void test(){
        ImmutableList<Person> people = ImmutableList.of();
    }
}

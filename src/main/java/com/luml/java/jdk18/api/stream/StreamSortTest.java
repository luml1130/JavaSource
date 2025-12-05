package com.luml.java.jdk18.api.stream;

import com.luml.domain.User2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2025/11/28
 */
public class StreamSortTest {

    public static void main(String[] args) {
       User2 u1  = new User2();
       u1.setId(1);
       u1.setName("11111");

        User2 u2  = new User2();
        u2.setId(2);
        u2.setName("22222");

    }

    //使用Lambda表达式自定义排序
    public static void main2(String[] args) {
        List<String> strings = Arrays.asList("banana", "apple", "oew");
        List<String> sortedStrings = strings.stream()
                .sorted()
                .sorted((s1, s2) -> s2.length() - s1.length()) // 根据字符串长度降序排列
                //升序？
                .collect(Collectors.toList());
        System.out.println(sortedStrings); // 输出可能为: [banana, orange, oew]（取决于长度）

    }
}

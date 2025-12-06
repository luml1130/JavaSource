package com.luml.java.tools.guava;

import com.google.common.collect.ImmutableList;
import com.luml.domain.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luml
 * @description:
 *      ImmutableList.of() 适用于快速创建小规模、不可变的列表场景，强调安全性和性能。若需处理大数据量或更复杂的构建逻辑，可考虑其他 Guava 方法或 Java 9+ 的原生选项。‌
 * @date 2025/12/6
 */
public class ImmutableListTest {


    public static void main(String[] args) {
        //ImmutableList.of();
        ImmutableList<Person> people = ImmutableList.of();

        //copyOf()‌：可用于从现有集合（如 ArrayList）创建不可变副本：
        List<String> list = new ArrayList<>(Arrays.asList("沉默王二", "沉默王三"));
        ImmutableList<String> unmodifiableList = ImmutableList.copyOf(list);

        //builder() 模式‌：支持动态构建不可变列表，结合 add() 和 addAll() 方法：‌
        ImmutableList<String> iList = ImmutableList.<String>builder()
                .add("沉默王二", "沉默王三")
                .build();


    }
}

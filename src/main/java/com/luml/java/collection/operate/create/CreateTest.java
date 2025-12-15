package com.luml.java.collection.operate.create;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.luml.domain.Person;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author luml
 * @description
 * @date 2025/12/15
 */
public class CreateTest {
    public static void main(String[] args) {

    }

    /**
     * Google Guava
     */
    @Test
    public void googleTools(){
        List<Person> iTreeVOList = Lists.newArrayList();
        Set<Person> hashSet =  Sets.newHashSet();
        Map<String,Person> personMap = Maps.newHashMap();

        //不变容器 list集合初始化的时候，若大小可知，应初始化固定大小的集合，
        // 避免在add()元素的时候，要扩容，然后就得重新copy一遍元素
        List<Long> crowdIdList = Lists.newArrayListWithCapacity(2);

        /**
         * ImmutableList.of() 适用于快速创建小规模、不可变的列表场景，强调安全性和性能。
         * 若需处理大数据量或更复杂的构建逻辑，可考虑其他 Guava 方法或 Java 9+ 的原生选项。‌
         */
        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
        ImmutableSet<String> iSet = ImmutableSet.of("e1", "e2");
        ImmutableMap<String, String> iMap = ImmutableMap.of("k1", "v1", "k2", "v2");

        ImmutableList<Person> people = ImmutableList.of();
    }

    public void java19(){
        com.sun.tools.javac.util.List<Person> people = com.sun.tools.javac.util.List.of(
                new Person("Alice", 1),
                new Person("Bob", 2),
                new Person("Charlie", 3)
        );
        //List<String> emptyList = List.of(); // 创建空列表  1.9不报错
        com.sun.tools.javac.util.List<String> singleElement = com.sun.tools.javac.util.List.of("apple");// 创建单元素列表
        com.sun.tools.javac.util.List<String> multiElements = com.sun.tools.javac.util.List.of("apple", "banana", "cherry");// 创建多元素列表
    }
}

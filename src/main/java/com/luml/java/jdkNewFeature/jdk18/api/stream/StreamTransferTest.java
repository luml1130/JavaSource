package com.luml.java.jdkNewFeature.jdk18.api.stream;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.luml.domain.Person2;
import com.luml.domain.User2;
import com.luml.java.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2025/12/5
 */
public class StreamTransferTest {

    /**
     * Collectors.toMap 是 Java Stream API 中一个非常实用的终端操作，用于将流中的元素转换为 Map 集合。
     */
    @Test
    public void testToMap(){
        //java.util.List<E6Pair<Integer, Long>> statusList = waybillInfoMapper.getStatusSummary(findWaybillPageParam(criteria));
        //Map<Integer, Integer> statusMap = statusList.stream().collect(Collectors.toMap(E6Pair::getFirst, item -> item.getSecond().intValue()));
        java.util.List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
            //add(new Person2("四木","simu",0,20));
        }};

        Map<Integer, String> genderMap = list.stream()
                .collect(Collectors.toMap(Person2::getGender, item -> item.getNickName()));
        System.out.println(genderMap); //{0=sanmu, 1=xiaoliu}
        //如果key冲突 比如 两个gender为0 的 java.lang.IllegalStateException: Duplicate key sanmu

        //或者如下写法
        /*Map<Integer, String> studentMap = list.stream()
                .collect(Collectors.toMap(Person2::getGender, Person2::getNickName));*/

        /**
         * 处理键冲突
         * 当流中存在生成相同键的元素时，如果不用第三个参数处理冲突，会抛出 IllegalStateException。‌
         * ‌第三个参数 mergeFunction‌ 是一个 BinaryOperator<U>，用于定义发生键冲突时的处理策略：
         * (existing, replacement) -> replacement：保留最后出现的元素（推荐常用）。‌
         * (existing, replacement) -> existing：保留第一个出现的元素。‌
         * 也可以进行更复杂的合并，例如将值合并成列表或进行数值累加。‌
         */
        list.add(new Person2("四木","simu",0,20));
        Map<Integer, Person2> studentMap = list.stream()
                .collect(Collectors.toMap(Person2::getGender, Function.identity(),
                        (existing, replacement) -> replacement));
        System.out.println("studentMap=" + studentMap);
        //studentMap={0=Person{name='四木', nickName='simu', gender=0, salary=20},
        //            1=Person{name='小刘', nickName='xiaoliu', gender=1, salary=30}}

    }

    @Test
    public void testToMap2(){

        /**
         * 1、指定 Map 实现类型
         * 默认情况下，toMap 返回 HashMap。如果需要特定的 Map 行为（如保持插入顺序或按键排序），
         *             可以使用第四个参数 mapSupplier 来指定 Map 的构造器。‌
         */
        java.util.List<User2> user2List = new ArrayList<User2>(){{
            add(new User2(1,"老鲁"));
            add(new User2(2,"桃子"));
            //add(new Person2("四木","simu",0,20));
        }};
        Map<Integer, User2> user2Map = user2List.stream()
                .collect(Collectors.toMap(User2::getId,Function.identity(),
                        (e, r) -> r, LinkedHashMap::new));//TreeMap::new
        //(e, r) -> r, ==  (existing, replacement) -> replacement)
        //System.out.println("user2Map="+ user2Map);
        //user2Map={1=com.luml.domain.User2@12bb4df8, 2=com.luml.domain.User2@4cc77c2e}
        System.out.println("user2Map="+ JSON.toJSONString(user2Map));
        //user2Map={1:{"id":1,"name":"老鲁"},2:{"id":2,"name":"桃子"}}

        /**
         * 2、安全处理 null 值
         * 在 Java 8 中，keyMapper 或 valueMapper 生成 null 键或值会抛出 NullPointerException。‌
         *  常见的处理方式是在收集前过滤掉 null 元素，或在映射函数中将 null 替换为默认值。
         */
        List<String> strings = Arrays.asList("apple", null, "banana");
        Map<String, Integer> lengthMap = strings.stream()
                .filter(Objects::nonNull)// 不加这一行java.lang.NullPointerException
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println("lengthMap="+lengthMap);
        //lengthMap={banana=6, apple=5}

        // 将 null 值替换为默认值
        user2List.add(new User2(3,null));
        Map<Integer, String> safeMap = user2List.stream()
                .collect(Collectors.toMap(
                        User2::getId,
                        p -> p.getName() != null ? p.getName() : "Unknown",
                        (existing, replacement) -> replacement)
                );
        System.out.println("safeMap="+safeMap);
        //safeMap={1=老鲁, 2=桃子, 3=Unknown}


    }

    //mapToInt/mapToLong/mapToDouble   list/map-->int
    @Test
    public void mapToIntLong() {
        com.sun.tools.javac.util.List<Person> peopleList = com.sun.tools.javac.util.List.of(
                new Person(1, "Alice"),
                new Person(2, "Bob"),
                new Person(3, "Charlie")
        );
        //int totalTransit = peopleList.stream().mapToInt(WaybillTemperatureVO::getTotalTransit).sum();
        int totalTransit = peopleList.stream().mapToInt(Person::getId).sum(); //id相加
        System.out.println(totalTransit);
    }






    //list--> map
    @Test
    public void list2Map() {
        List<Person> peopleList = com.sun.tools.javac.util.List.of(
                new Person(1, 12,"Alice"),
                new Person(2, 15,"Bob"),
                new Person(3, 9,"Charlie")
        );
       /* Map<Integer, Person> personMap = people.stream()
                .collect(Collectors.toMap(Person::getId, p -> p));*/

        //list先排序 再转换tomap
        //Collections.sort(peopleList,Comparator.comparing(Person::getFullName, Comparator.naturalOrder()));


        System.out.println(peopleList);

        /*Map<Integer, String> ageToNameMap = peopleList.stream()
                .collect(Collectors.toMap(Person::getId, Person::getFullName));
        System.out.println(ageToNameMap);*/

        /*Map<Integer, Person> alarmStatPOMap = new HashMap<>();//Collections.emptyMap();
        people.stream().forEach(person -> {
            alarmStatPOMap.put(person.getId(), person);
        });
        System.out.println(alarmStatPOMap);*/

    }


}

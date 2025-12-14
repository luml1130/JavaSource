package com.luml.java.jdkNewFeature.jdk18.api.stream;

import com.luml.domain.Person2;
import com.luml.java.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2025/12/5
 */
public class StreamTransferTest {

    @Test
    public void testToMap(){
        //java.util.List<E6Pair<Integer, Long>> statusList = waybillInfoMapper.getStatusSummary(findWaybillPageParam(criteria));
        //Map<Integer, Integer> statusMap = statusList.stream().collect(Collectors.toMap(E6Pair::getFirst, item -> item.getSecond().intValue()));
        java.util.List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
        }};
        Map<Integer, String> genderMap = list.stream()
                .collect(Collectors.toMap(Person2::getGender, item -> item.getNickName()));
        System.out.println(genderMap); //{0=sanmu, 1=xiaoliu}

    }

    //mapToInt/mapToLong/mapToDouble   list/map-->int
    public static void main2(String[] args) {
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

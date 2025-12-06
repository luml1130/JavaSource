package com.luml.java.jdkNewFeature.jdk18.api.stream;

import com.luml.domain.Person2;
import com.luml.java.data.Person;
import com.sun.tools.javac.util.List;
import org.junit.Test;

import java.util.ArrayList;
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
        List<Person> peopleList = List.of(
                new Person(1, "Alice"),
                new Person(2, "Bob"),
                new Person(3, "Charlie")
        );
        //int totalTransit = peopleList.stream().mapToInt(WaybillTemperatureVO::getTotalTransit).sum();
        int totalTransit = peopleList.stream().mapToInt(Person::getId).sum(); //id相加
        System.out.println(totalTransit);
    }

    //list--> map
    public static void main1(String[] args) {
        List<Person> people = List.of(
                new Person(1, "Alice"),
                new Person(2, "Bob"),
                new Person(3, "Charlie")
        );

        Map<Integer, Person> personMap = people.stream()
                .collect(Collectors.toMap(Person::getId, p -> p));

        System.out.println(personMap);
    }
}

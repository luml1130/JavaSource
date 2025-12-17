package com.luml.java.collection.operate.sort;

import com.luml.domain.sort.PersonNoCompare;
import com.luml.java.data.Person;
import org.hibernate.validator.constraints.br.TituloEleitoral;
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
 *  Comparator.comparingInt是Java 8引入的一个静态方法，用于创建一个比较器（Comparator），
 *  该比较器根据对象的某个int类型的属性或值来进行比较。它通常用于简化集合的排序操作，
 *  特别是当你需要根据对象的某个具体属性进行排序时。
 * @date 2025/12/11
 */
public class StreamSort_Collect_Test {

    @Test
    public void objectSortTest(){
        List<PersonNoCompare> personList  = new ArrayList<>();
        PersonNoCompare person1 = new PersonNoCompare(1, 13,"我是13");
        PersonNoCompare person2 = new PersonNoCompare(2, 21,"我是21");
        PersonNoCompare person3 = new PersonNoCompare(3, 19,"我是19");
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        //1、使用Comparator.comparing()方法
        List<PersonNoCompare> sortedList = personList.stream()
                       // .sorted(Comparator.comparing(PersonNoCompare::getAge))
                        //reverseOrder()方法返回一个比较器，用于对字段进行降序排序。
                        .sorted(Comparator.comparing(PersonNoCompare::getAge,Comparator.reverseOrder()))
                        .collect(Collectors.toList());
        //2、使用Comparator接口的lambda表达式 不对吧 是灰色的
        List<PersonNoCompare> lambdaSortedList = personList.stream()
                .sorted((p1, p2) -> p1.getFullName().compareTo(p2.getFullName()))
                .collect(Collectors.toList());


    }

    @Test
    public void listSort2Map(){
        List<PersonNoCompare> peopleList = new ArrayList<>();
        PersonNoCompare p = new PersonNoCompare(1, 13,"13");
        PersonNoCompare p1 = new PersonNoCompare(1, 21,"21");
        PersonNoCompare p2 = new PersonNoCompare(2, 19,"19");
        peopleList.add(p);
        peopleList.add(p1);
        peopleList.add(p2);

        //通过传入 Comparator 实例来定义排序规则，这种方式更灵活，尤其适合按不同属性排序。
        Collections.sort(peopleList, new Comparator<PersonNoCompare>() {
            @Override
            public int compare(PersonNoCompare s1, PersonNoCompare s2) {
                return s1.getFullName().compareTo(s2.getFullName()); // 按姓名字典序排序
            }
        });

        //使用 Lambda 表达式可以更简洁：
        //Collections.sort(peopleList, (s1, s2) -> s1.getFullName().compareTo(s2.getFullName())); //13 19 21
        Collections.sort(peopleList, Comparator.comparing(PersonNoCompare::getFullName));
        System.out.println(peopleList);//13 19 21

        // 使用 Comparator.comparingInt 进行降序排序
        peopleList.sort(Comparator.comparingInt(PersonNoCompare::getAge).reversed());
        System.out.println(peopleList); // 按年龄 21 19 13


    }


}

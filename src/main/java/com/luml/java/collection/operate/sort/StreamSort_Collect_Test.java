package com.luml.java.collection.operate.sort;

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
 *  Comparator.comparingInt是Java 8引入的一个静态方法，用于创建一个比较器（Comparator），
 *  该比较器根据对象的某个int类型的属性或值来进行比较。它通常用于简化集合的排序操作，
 *  特别是当你需要根据对象的某个具体属性进行排序时。
 * @date 2025/12/11
 */
public class StreamSort_Collect_Test {


    @Test
    public void listSort2Map(){
        List<Person> peopleList = new ArrayList<>();
        Person p = new Person(1, 13,"13");
        Person p1 = new Person(1, 21,"21");
        Person p2 = new Person(2, 19,"19");
        peopleList.add(p);
        peopleList.add(p1);
        peopleList.add(p2);

        //通过传入 Comparator 实例来定义排序规则，这种方式更灵活，尤其适合按不同属性排序。
        /*Collections.sort(peopleList, new Comparator<Person>() {
            @Override
            public int compare(Person s1, Person s2) {
                return s1.getFullName().compareTo(s2.getFullName()); // 按姓名字典序排序
            }
        });*/

        //使用 Lambda 表达式可以更简洁：
        //Collections.sort(peopleList, (s1, s2) -> s1.getFullName().compareTo(s2.getFullName())); //13 19 21
        Collections.sort(peopleList, Comparator.comparing(Person::getFullName));
        System.out.println(peopleList);//13 19 21

        // 使用 Comparator.comparingInt 进行降序排序
        peopleList.sort(Comparator.comparingInt(Person::getAge).reversed());
        System.out.println(peopleList); // 按年龄 21 19 13


    }

}

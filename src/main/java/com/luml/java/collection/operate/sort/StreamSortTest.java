package com.luml.java.collection.operate.sort;

import com.luml.domain.Person;
import com.luml.domain.sort.PersonSort;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2025/11/28
 */
public class StreamSortTest {

    public static void main(String[] args) {
        System.out.println(12-15);
    }

    //使用Lambda表达式自定义排序
    @Test
    public void lambdaSort(){
        List<String> strings = Arrays.asList("banana", "apple", "oew");
        List<String> sortedStrings = strings.stream()
                .sorted()
                .sorted((s1, s2) -> s2.length() - s1.length()) // 根据字符串长度降序排列
                //升序？
                .collect(Collectors.toList());
        System.out.println(sortedStrings); // 输出可能为: [banana, orange, oew]（取决于长度）

    }


    @Test
    public void sort(){
        //对于自定义类
        List<PersonSort> personList = new ArrayList<PersonSort>(){{
            add(new PersonSort("Bob",12));
            add(new PersonSort("Alice",13));
        }};

        List<Person> personList2 = personList.stream()
                .map(f -> {
                    Person vo = new Person();
                    vo.setAge(f.getAge());
                    vo.setName(f.getName());
                    return vo;
                })
                //.sorted((s1, s2) -> s2.getAge() - s1.getAge())//年龄从大到小
                //.sorted(Comparator.comparing(Person::getAge, Comparator.reverseOrder())) //reverseOrder降序
                .sorted(Comparator.comparing(Person::getAge, Comparator.naturalOrder())) //naturalOrder升序
                .collect(Collectors.toList());
        System.out.println(personList2);



    }

    /**
     * 2、自定义排序
     * 如果你需要对流中的元素根据某个特定的规则进行排序，你可以传递一个 Comparator 接口的实现给 sorted() 方法。
     * @param args
     */
    public static void main3(String[] args) {
        List<String> strings = Arrays.asList("banana", "apple", "orange");
        List<String> sortedStrings = strings.stream()
                .sorted(Comparator.reverseOrder()) // 降序排列
                .collect(Collectors.toList());
        System.out.println(sortedStrings); // 输出: [orange, banana, apple]

    }

    /**
     * 效果同自然排序
     * @param args
     */
    public static void main2(String[] args) {
        PersonSort p1 = new PersonSort("Alice", 30);
        PersonSort p2 = new PersonSort("Bob", 25);
        PersonSort p3 = new PersonSort("Charlie", 35);

        List<PersonSort> personSortList= Arrays.asList(p1, p2, p3);
        Collections.sort(personSortList); // 使用compareTo方法进行排序  效果同自然排序
        System.out.println(personSortList);
    }

    /**
     * 1、自然排序通常是基于元素自身的Comparable接口实现的。这意味着，如果你想要排序的元素实现了Comparable接口，
     *                  那么你可以直接调用sorted()方法而不需要提供任何比较器。
     * @param args
     */
    public static void main1(String[] args) {
        //对于自定义类
        List<PersonSort> personList = new ArrayList<PersonSort>(){{
            add(new PersonSort("Bob",12));
            add(new PersonSort("Alice",13));
        }};
        List<PersonSort> sortedPeople = personList.stream()
                .sorted() // 使用自然排序，根据Person的compareTo方法实现
                .collect(Collectors.toList());

        sortedPeople.forEach(personSort -> System.out.println(personSort.getName() + " - " + personSort.getAge()));
        //按名称排序 输出: Alice - 13   Bob - 12
        //按年龄排序 输出：Bob - 12 Alice - 13
        //按年龄排序 输出： Alice - 13 Bob - 12


        //自然排序
        List<String> strings = Arrays.asList("banana", "apple", "orange");
        List<String> sortedStrings = strings.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sortedStrings); // 输出: [apple, banana, orange]


    }

}

package com.luml.java.jdkNewFeature.jdk18.api.stream;

import com.luml.domain.Person;
import com.luml.domain.Person2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * 聚合、分组
 * counting()：统计元素数量。
 * averagingInt/Double/Long()：计算平均值。
 * summingInt/Double/Long()：求和。
 * summarizingInt/Double/Long()：一次性获取统计信息（如最大值、最小值、总和等）。
 *
 * joining()：将元素连接为字符串，可指定分隔符。
 *
 * reducing()：自定义归约逻辑（如计算总和并减去固定值）。
 *  Java 8 引入的一个收集器方法，用于将流（Stream）中的元素通过累积操作合并成一个单一的结果，常用于求和、求最大值、最小值等聚合操作。‌1
 * @date 2025/12/5
 */
public class StreamOtherTest {

    //reducing()
    public static void main(String[] args) {
        List<Person2> personList = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,100));
            add(new Person2("李四","zhangsan",1,200));
        }};
        Integer sumSal = personList.stream()
                .collect(Collectors.reducing(100, Person2::getSalary, (x, y) -> x + y - 5000));
        //初始值100 +（100-5000) + (200-5000) =
        System.out.println(sumSal); //-9600

        //拼接
        List<String> stringCollection = Arrays.asList("apple",
                "banana", "cherry", "date", "elderberry");
        Optional<String> reduced =
                stringCollection
                        .stream().sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);//apple#banana#cherry#date#elderberry


        //求和（无初始值）‌
         List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
         Optional<Integer> sum = numbers.stream().collect(Collectors.reducing((a, b) -> a + b));
         sum.ifPresent(System.out::println); // 输出: 15

        //求和（有初始值）‌
        int sum2 = numbers.stream().collect(Collectors.reducing(2, (a, b) -> a + b));
        System.out.println(sum2); // 初始值是2 输出: 17

        //求最大值/最小值‌
        Optional<Integer> max = numbers.stream().collect(Collectors.reducing(Integer::max));
        max.ifPresent(System.out::println); // 输出: 5

        Optional<Integer> min = numbers.stream().collect(Collectors.reducing(Integer::min));
        min.ifPresent(System.out::println); // 输出: 1



    }

    //.joining 拼接
    public static void main4(String[] args) {
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
        }};

        String names = list.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println(names); //张三,李四,王五,小刘,三木

    }

    //counting()：统计元素数量。
    public static void main3(String[] args) {
        List<String> stringCollection = Arrays.asList("apple",
                "banana", "cherry", "date", "elderberry");

        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();
        System.out.println(startsWithB);    // 3
    }
    //averagingInt/Double/Long()
    public static void main2(String[] args) {
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setAge(12);
        personList.add(person);
        Person person2 = new Person();
        person.setAge(15);
        personList.add(person2);

        Double avgAge = personList.stream()
                .collect(Collectors.averagingDouble(Person::getAge));
        System.out.println(avgAge);
    }
}

package com.luml.java.jdkNewFeature.jdk18.api.stream;

import com.luml.domain.Person;
import com.luml.domain.Person2;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
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


        //拼接
        List<String> stringCollection = Arrays.asList("apple",
                "banana", "cherry", "date", "elderberry");
        Optional<String> reduced =
                stringCollection
                        .stream().sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);//apple#banana#cherry#date#elderberry


    }

    @Test
    public  void NumCount(){
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


    @Test
    public void objectCount(){
        List<Person2> personList = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,100));
            add(new Person2("李四","zhangsan",1,200));
        }};
        //1、初始值100 +（100-5000) + (200-5000) =
        Integer sumSal = personList.stream()
                .collect(Collectors.reducing(100, Person2::getSalary, (x, y) -> x + y - 5000));
        System.out.println(sumSal); //-9600

        //2、Integer
        Integer integerSum = personList.stream()
                .map(Person2::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("integerSum="+integerSum); //integerSum=300

        //3、bigDecimalSum
        List<Person2> person2List = new ArrayList<Person2>(){{
            add(new Person2("张三",0,new BigDecimal(12.123)));
            add(new Person2("李四",1,new BigDecimal(12.124)));
        }};
        Integer bigDecimalSum = person2List.stream()
                .map(Person2::getAmount)
                .mapToInt(BigDecimal::intValue)
                .sum();
        System.out.println("bigDecimalSum="+bigDecimalSum);//bigDecimalSum=24

        //4、使用reduce方法来计算总和
        // 创建一个BigDecimal的列表
        List<BigDecimal> numbers = Arrays.asList(
                new BigDecimal("10.5"),
                new BigDecimal("20.3"),
                new BigDecimal("35.7")
        );
        //4.1、使用reduce方法来计算总和
       BigDecimal bigDecimalSum2 = numbers.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("bigDecimalSum2="+bigDecimalSum2);

        //4.2、Object
        List<Person2> person3List = new ArrayList<Person2>(){{
            add(new Person2("张三",0,new BigDecimal(12.123)));
            add(new Person2("李四",1,new BigDecimal(12.124)));
        }};
        List<BigDecimal> amountList = person3List.stream()
                .map(Person2::getAmount)
                .collect(Collectors.toList());
        BigDecimal objectDecimalSum = amountList.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                //保留3位 四舍五入
                .setScale(3, RoundingMode.HALF_UP);
        System.out.println("objectDecimalSum="+objectDecimalSum);



    }

    //.joining 拼接
    @Test
    public  void joinTest(){
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
        }};

        String names = list.stream()
                .map(p -> p.getName())
                .collect(Collectors.joining(","));
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

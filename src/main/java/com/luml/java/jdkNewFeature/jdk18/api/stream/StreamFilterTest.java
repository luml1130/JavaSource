package com.luml.java.jdkNewFeature.jdk18.api.stream;

import com.luml.domain.Person2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2025/11/20
 */
public class StreamFilterTest {

    @Test
    public void JiaoJiTest(){
        List<String> list1 = Arrays.asList("apple", "banana", "cherry");
        List<String> list2 =  Arrays.asList("banana", "date", "fig");

        boolean hasIntersection = list1.stream()
                .anyMatch(list2::contains);

        System.out.println("List1: " + list1);
        System.out.println("List2: " + list2);
        System.out.println("Has Intersection: " + hasIntersection);

    }

    //过滤字符串列表中的特定条件
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple",
                "banana", "cherry", "date", "elderberry");
        // 过滤出所有以 'a' 开头的字符串
        List<String> startsWithA = words.stream()
                .filter(word -> word.startsWith("a"))
                .collect(Collectors.toList());

        System.out.println(startsWithA); // 输出: [apple, elderberry]
    }

    //3、使用 filter 方法过滤偶数
    public static void main2(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evenNumbers); // 输出: [2, 4, 6, 8, 10]
    }
    public static void main1(String[] args) {
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
        }};

        //Set<Integer> vehicleIds = resultRecords.stream().map(EventReportInfoPO::getVehicleId).filter(Objects::nonNull).collect(Collectors.toSet());

        list.stream().
                filter(Person2 -> Person2.getGender() == 1 )
                .filter(Person2 -> Person2.getNickName().startsWith("x"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        //Person{name='小刘', nickName='xiaoliu', gender=1, salary=30}

        /*List<Person2> list2 = list.stream().
                filter(Person2 -> Person2.getGender() ==1 )
                //filter((s) -> s.startsWith("a"))
                .collect(Collectors.toList());*/

    }
}

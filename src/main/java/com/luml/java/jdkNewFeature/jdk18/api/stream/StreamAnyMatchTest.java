package com.luml.java.jdkNewFeature.jdk18.api.stream;

import com.luml.domain.Person2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2025/12/5
 */
public class StreamAnyMatchTest {



    //anyMatch  allMatch  noneMatch
    public static void main(String[] args) {
        List<String> stringCollection = Arrays.asList("apple",
                "banana", "cherry", "date", "elderberry");

        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false
        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneStartsWithZ);      // true

        //return *****(对象list).stream().anyMatch(indi -> indi.getCounts() > 0);

    }


    //对象判断
    @Test
    public void match(){
       Person2 p2 = new Person2("张三","zhangsan",0,10);
       List<Person2> p2List = new ArrayList<>();
       Integer a = 1;
        boolean isHaveSyncDate =
                p2List
                        .stream()
                        .anyMatch((s) -> p2List.equals(s.getGender()));
        System.out.println(isHaveSyncDate);

        isFalse(isHaveSyncDate, "包含了不能");
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            System.out.println(message);
        }
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            System.out.println(message);
        }
    }
}

package com.luml.java.jdkNewFeature.jdk18.api.stream;

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
}

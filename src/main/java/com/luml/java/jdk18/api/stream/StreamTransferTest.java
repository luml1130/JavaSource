package com.luml.java.jdk18.api.stream;

import com.luml.java.data.Person;
import com.sun.tools.javac.util.List;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2025/12/5
 */
public class StreamTransferTest {

    //list--> map
    public static void main(String[] args) {
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

package com.luml.utiltools.lombok;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private int age;
    private String address;

    public static void main(String[] args) {
        // 使用示例
        Person person = Person.builder()
                .name("Alice")
                .age(25)
                .address("Beijing")
                .build();
    }
}

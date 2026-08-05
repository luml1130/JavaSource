package com.luml.test;

import com.luml.domain.User;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;
// 下面这两个是错误的哦
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.assertj.core.api.AssertionsForClassTypes.within;


public class asserTjTest {

    @Test
    void testUsingAssertJ() {
        assertThat(1).isEqualTo(1);
    }

    /**
     * 1、基础数据类型断言
     * AssertJ 支持整数、浮点数、布尔值等的链式断言。
     */
    @Test
    void testBasicTypes() {
        // 整数断言
        assertThat(10).isEqualTo(10)
                .isGreaterThan(5)
                .isLessThan(20)
                .isBetween(5, 20);

        // 浮点数断言（处理精度问题）
        assertThat(3.14159).isCloseTo(3.14, within(0.01));

        // 布尔断言
        assertThat(true).isTrue();
        assertThat(false).isFalse();
    }

    /**
     * 2、字符串断言
     * 提供丰富的字符串校验方法，如非空、前后缀、包含子串等。
     */
    @Test
    void testStringAssertions() {
        String message = "Hello, AssertJ World!";

        assertThat(message).isNotNull()
                .isNotEmpty()
                .startsWith("Hello")
                .endsWith("World!")
                .contains("AssertJ")
                .doesNotContain("JUnit")
                .hasSize(22)
                .isEqualToIgnoringCase("hello, assertj world!");

        //正则匹配
        assertThat(message).matches("^Hello.*World!$");
    }

    /**
     * 3、集合与数组断言
     * 这是 AssertJ 的强项，支持对 List、Set、Map 及数组进行深度校验。
     */
    @Test
    void testCollectionAssertions() {
        List<String> fruits = Arrays.asList("apple", "banana", "cherry");

        // 基础集合属性
        assertThat(fruits).isNotEmpty()
                .hasSize(3)
                .doesNotHaveDuplicates();

        // 元素内容校验
        assertThat(fruits).contains("apple", "banana")
                .doesNotContain("orange")
                .containsExactly("apple", "banana", "cherry") // 严格顺序
                .containsSequence("banana", "cherry"); // 连续序列

        // 数组断言
        int[] numbers = {1, 2, 3};
        assertThat(numbers).contains(1, 2)
                .doesNotContain(4);
    }

    /**
     * 对象与复杂结构断言
     * 支持直接验证对象的字段值，甚至可以从集合中提取特定字段进行断言。
     */
    @Test
    void testObjectAssertions() {
        // 假设有一个 User 类
        User user = new User("john_doe", "17799999");

        // 验证对象非空及字段值
        assertThat(user).isNotNull()
                .hasFieldOrProperty("username")
                .hasFieldOrPropertyWithValue("mobile", "john@example.com");

        // 提取字段进行断言
       // assertThat(user).extracting(User::getUsername)
        assertThat(user).extracting(user.getName())
                .isEqualTo("john_doe");

        // 多字段提取
        assertThat(user).extracting(user.getName(), user.getMobile())
                .containsExactly("john_doe", 30);
    }

    /**
     * 5、集合中对象字段的提取断言：‌
     */
    @Test
    void testExtractingFromList() {
        List<User> users = Arrays.asList(
                //new User(1L, "Alice", "alice@test.com", 25),
                //new User(2L, "Bob", "bob@test.com", 30)
                new User("Alice", "13533887879"),
                new User("Bob", "185019979222")
        );
        // 提取所有用户的名字进行断言
        assertThat(users).extracting(User::getName)
                .containsExactly("Alice", "Bob")
                .doesNotContain("Charlie");
    }

    /**
     * 6. 异常断言
     * 验证代码是否抛出了预期的异常，并检查异常信息。
     */
    @Test
    void testExceptionAssertions() {
        // 验证是否抛出特定异常
        assertThatThrownBy(() -> {
            throw new IllegalArgumentException("Invalid input");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid input")
                .hasMessageContaining("Invalid");

        // 另一种写法：assertThatCode
        assertThatCode(() -> {
            // 正常执行不抛异常的代码
            System.out.println("No exception");
        }).doesNotThrowAnyException();
    }


    /**
     * Optional 断言
     * Java 8+ 的 Optional 类型支持。
     */
    @Test
    void testOptionalAssertions() {
        Optional<String> optionalValue = Optional.of("Hello");

        assertThat(optionalValue).isPresent()
                .contains("Hello")
                .hasValue("Hello");

        Optional<String> emptyOptional = Optional.empty();
        assertThat(emptyOptional).isEmpty();
    }

    /**
     * 8. Map 断言
     */
    @Test
    void testMapAssertions() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 90);
        scores.put("Bob", 85);

        assertThat(scores).hasSize(2)
                .containsKey("Alice")
                .doesNotContainKey("Charlie")
                .containsEntry("Alice", 90)
                .containsValues(90, 85);
    }




}

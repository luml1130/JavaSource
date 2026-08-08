package com.test;

import com.luml.test.Calculator1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Junit5Test {
    //如果没有 @BeforeEach注解则 需要new 处理
    private Calculator1 calculator;// = new Calculator1();
    @BeforeEach
    void setUp() {
        calculator = new Calculator1(); // 在每个测试方法之前初始化Calculator对象。
    }

    @Test
    void testAdd() {
        int result = calculator.add(2, 3);
        assertEquals(5, result, "2 + 3 should equal 5");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testWithIntegers(int argument) {
        assertNotNull(argument);
    }


}

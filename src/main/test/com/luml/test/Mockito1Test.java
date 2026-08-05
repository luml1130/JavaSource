package com.luml.test;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Mockito1Test {

    @Mock // 标记为模拟对象
    private Database database;

    @InjectMocks // 自动注入模拟对象到被测试对象中
    private Mockito1 mockito1;

    public Mockito1Test() {
        MockitoAnnotations.openMocks(this); // 初始化Mockito注解
    }

    /**
     * error 待解决
     */
    @Test
    public void testAdd() {
        System.out.println("22");
        // 定义模拟对象的行为
        //when(database.getData()).thenReturn(5); // 当调用getData()时，返回5
        //int result = mockito1.add(3, 2); // 调用被测试的方法
        //assertEquals(10, result); // 断言结果是否正确
        //verify(database, times(1)).getData(); // 验证getData()方法是否被调用了一次
    }
}

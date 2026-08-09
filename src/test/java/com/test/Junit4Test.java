package com.test;


import com.luml.test.Calculator1;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author luml
 * @description
 * Junit5 test文件必须写在test类型包下。
 * @date 2021/8/23 上午11:47
 */
public class Junit4Test {

    /**
     * Junit4
     */
    @Test
    public void testAdd() {
        Calculator1 calculator = new Calculator1();
        assertEquals(5, calculator.add(2, 3)); // 期望结果是5，2+3等于5
    }


}

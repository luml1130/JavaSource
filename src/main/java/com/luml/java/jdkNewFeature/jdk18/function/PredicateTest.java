package com.luml.java.jdkNewFeature.jdk18.function;

import org.junit.Test;

import java.util.function.Predicate;

/**
 * @author luml
 * @description
 * @date 2022/4/5
 */
public class PredicateTest {


    @Test
    public void test01(){

        PredicateTest predicateTestOne = new PredicateTest();

        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equals("zhangsan");
            }
        };

        System.out.println(predicate.test("lisi"));
        //false
        System.out.println("--- --- --- --- --- ---");
        System.out.println(predicate.test("zhangsan"));
        //tue
    }
}

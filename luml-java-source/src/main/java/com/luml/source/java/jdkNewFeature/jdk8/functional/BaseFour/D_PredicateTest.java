package com.luml.source.java.jdkNewFeature.jdk8.functional.BaseFour;

import org.junit.Test;

import java.util.function.Predicate;

/**
 * @author luml
 * @description
 * @date 2022/4/5
 */
public class D_PredicateTest {


    @Test
    public void test01(){

        D_PredicateTest predicateTestOne = new D_PredicateTest();

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

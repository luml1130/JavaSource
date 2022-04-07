package com.luml.java.jdk18.function;

import org.junit.Test;

import java.util.function.Predicate;

/**
 * @author luml
 * @description:
 * (采用传统的方式和新的方式)
 *     1.判断传入的字符串的长度是否大于5
 *     2.判断传入的参数是否是偶数
 *     3.判断数字是否大于10
 * @date 2022/4/5
 */
public class PredicateTestOne {

    public static void main(String[] args) {
        /** 我们先采用传统的方式 */
        /**  - 1.判断传入的字符串的长度是否大于5 */
        PredicateTestOne predicateTestOne = new PredicateTestOne();
        System.out.println(predicateTestOne.judgeStringLength("hello"));
        System.out.println(predicateTestOne.judgenumbersOdds(4));
        System.out.println(predicateTestOne.judgeSpecialNumbers(-1));
    }

    /**
     * Java8中的方法
     */
    @Test
    public void test() {
        PredicateTestOne predicate = new PredicateTestOne();
        /** - 1.判断传入的字符串的长度是否大于5 */
        System.out.println(predicate.judgeConditionByFunction(12345,value -> String.valueOf(value).length() > 5));
        /** - 2.判断传入的参数是否是奇数 */
        System.out.println(predicate.judgeConditionByFunction(4,value -> value % 2 == 0));
        /** - 3.判断数字是否大于10 */
        System.out.println(predicate.judgeConditionByFunction(-1, value-> value > 10));
    }

    public boolean judgeConditionByFunction(int value, Predicate<Integer> predicate) {
        return predicate.test(value);
    }

    /**
     *
     * - 1.判断传入的字符串的长度是否大于5
     * @param judgeString 待判断字符串
     * @return
     */
    public boolean judgeStringLength(String judgeString) {
        return judgeString.length() > 5 ? true:false;
    }

    /**
     * - 2.判断传入的参数是否是奇数
     * @param number        待判断的数字
     * @return               1 代表偶数， 0代表奇数
     */
    public int judgenumbersOdds(int number) {
        return number % 2 == 0 ? 1 : 0;
    }

    /**
     * - 3.判断数字是否大于10
     * @param number        待判断的数字
     * @return               1. 代表大于10 ， 0 代表小于10
     */
    public int judgeSpecialNumbers(int number) {
        return number > 10 ? 1 : 0;
    }

}

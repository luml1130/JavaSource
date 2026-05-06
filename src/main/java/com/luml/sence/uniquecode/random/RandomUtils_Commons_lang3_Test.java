package com.luml.sence.uniquecode.random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * @author luml
 * @description
 *  RandomStringUtils:org.apache.commons.lang3.RandomStringUtils;
 * @date 2026/3/28
 */
public class RandomUtils_Commons_lang3_Test {

    @Test
    public void getRandom(){
        System.out.println(RandomUtils_Commons_lang3_Test.getRandomString(5));
        //RMKDW

    }
    /**
     * 生成随机字符串
     */
    public static String getRandomString(Integer length) {
        return RandomStringUtils.random(length, true, false).toUpperCase();
    }
}

package com.luml.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author luml
 * @description
 * @date 2026/3/28
 */
public class RandomUtils {

    /**
     * 生成随机字符串
     */
    public static String getRandomString(Integer length) {
        return RandomStringUtils.random(length, true, false).toUpperCase();
    }
}

package com.luml.java.javaclass.data.String;

import org.apache.commons.lang3.StringUtils;

/**
 * @author luml
 * @description
 * @date 2022/11/9
 */
public class StringUtilsTest {
    public static void main(String[] args) {
        String permanentCode = null;
        System.out.println(StringUtils.isBlank(permanentCode) ? false : true);
        System.out.println(StringUtils.isBlank(null));
    }
}

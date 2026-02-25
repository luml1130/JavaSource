package com.luml.java.javaclass.data.String;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void test(){
        String accStatusStr = "wo";
        String loStatusStr = "ni";
        String lineStatusStr = "te";
        String status = StringUtils.join(Arrays.asList(accStatusStr,loStatusStr,lineStatusStr),",");
        System.out.println(status);
    }
}

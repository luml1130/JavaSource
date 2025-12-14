package com.luml.java.nature.pattern;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luml
 * @description
 * @date 2025/12/12
 */
public class PatternTest01 {

    @Test
    public void splitProvince(){
        //String fullAddress = "广东省-深圳市 南山区"; // 使用不同的分隔符，如破折号或连字符等
       // String fullAddress = "广东省 深圳市 南山区"; //OK
         String fullAddress = "广东省-深圳市-南山区"; //OK
        //String fullAddress = "广东省深圳市南山区"; //NO
        Pattern pattern = Pattern.compile("([\\p{L}]+)\\s*[\\-\\s]*\\s*([\\p{L}]+)\\s*[\\-\\s]*\\s*([\\p{L}]+)");
        // 使用Unicode字母匹配省份、城市和区域名，并允许空格、破折号或连字符作为分隔符
        Matcher matcher = pattern.matcher(fullAddress);

        if (matcher.find()) {
            String province = matcher.group(1);
            String city = matcher.group(2);
            String district = matcher.group(3);

            System.out.println("省份: " + province);
            System.out.println("城市: " + city);
            System.out.println("区域: " + district);
        } else {
            System.out.println("地址格式不正确");
        }
    }
}

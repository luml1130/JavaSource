package com.luml.java.javaclass.data.String;

import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2025/12/14
 */
public class StringOperateTest {

    @Test
    public void split(){
        String fullAddress = "广东省 深圳市 南山区";
        String[] addressParts = fullAddress.split(" ");

       // String fullAddress = "广东省 深圳市\n南山区"; // 注意这里的换行符
       // String[] addressParts = fullAddress.split("\\s+"); // \\s+ 匹配一个或多个空白符

        if (addressParts.length == 3) {
            String province = addressParts[0];
            String city = addressParts[1];
            String district = addressParts[2];

            System.out.println("省份: " + province);
            System.out.println("城市: " + city);
            System.out.println("区域: " + district);
        } else {
            System.out.println("地址格式不正确");
        }

        //方法3：使用Pattern和Matcher类（更灵活） 具体见正则包
    }

}

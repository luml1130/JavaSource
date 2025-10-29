package com.luml.java.tools.apache.commons;

import com.luml.domain.City;
import org.apache.commons.lang3.StringUtils;

/**
 * @author luml
 * @description
 * @date 2025/10/29
 */
public class StringUtilsTest {

    public static void main(String[] args) {
        City c = new City();
        c.setAddress("ddd");
        //测试没有设置的属性会不会报空指针，不会
        if(StringUtils.isNotBlank(c.getProvince())){
            System.out.println("不为空");
        }
    }
}

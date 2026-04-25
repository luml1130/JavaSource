package com.luml.java.jdkNewFeature.jdk18.api.Stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2021/9/18 下午3:38
 */
public class test {
    public static final String GET_LOGIN_INFO =  "/qw-platform/api/v1/mix/get_login_info?authCode=%s";

    public static void main(String[] args) {
        List<String> nodeAreaNameList = new ArrayList<>();
        nodeAreaNameList.add("北京");
        nodeAreaNameList.add("上海");
        System.out.println(String.join(" -> ", nodeAreaNameList));


    }
}

package com.luml.java.jdkNewFeature.jdk18.api.stream;

import com.luml.domain.Person2;
import com.luml.java.jdkNewFeature.jdk18.WayBillWeightPhoto;

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
    public static void main2(String[] args) {
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,40));
            add(new Person2("三木","sanmu",0,50));
        }};


    }
}

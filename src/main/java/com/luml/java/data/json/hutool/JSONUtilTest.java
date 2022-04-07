package com.luml.java.data.json.hutool;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luml.java.data.Person;

import java.util.Date;

/**
 * @author luml
 * @description
 * @date 2022/4/5
 */
public class JSONUtilTest {

    public static void main(String[] args) {
        Person person = new Person(1,15, "John Doe", new Date());
        String signStr = JSONUtil.toJsonStr(person);
        System.out.println(signStr);
        System.out.println(JSON.toJSONString(signStr));
        System.out.println(JSONObject.toJSON(signStr));
        /**
         * {"fullName":"John Doe","dateOfBirth":1649164153030,"id":1,"age":15}
         * "{\"fullName\":\"John Doe\",\"dateOfBirth\":1649164153030,\"id\":1,\"age\":15}"
         * {"fullName":"John Doe","dateOfBirth":1649164153030,"id":1,"age":15}
         */
    }
}

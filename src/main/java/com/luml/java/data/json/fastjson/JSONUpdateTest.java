package com.luml.java.data.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.luml.domain.UserJson;

/**
 * @author luml
 * @description
 *  测试：json数据少 对象属性对  没问题
 *       json数据少 对象属性多 没问题
 * @date 2025/12/2
 */
public class JSONUpdateTest {

    public static void main(String[] args) {
        //UserJson userJson = new UserJson();
        //userJson.setName("lu");
        //userJson.setMobile("177");

        String json = "{\"name\":\"lu\",\"mobile\":\"17299990\"}";
        UserJson userJson1 =  JSON.parseObject(json, UserJson.class);
        System.out.println(userJson1.getName());
    }
}

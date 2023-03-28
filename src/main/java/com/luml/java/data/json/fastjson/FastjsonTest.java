package com.luml.java.data.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luml.domain.City;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Map;

/**
 * @author luml
 * @description
 * @date 2021/11/23
 */
public class FastjsonTest {

    public static void main(String[] args) {
        String bb = getUnionId();
        if(StringUtils.isBlank(bb)){
            System.out.println("empby");
        }
    }

    private static String getUnionId(){
        City c = new City();
        c.setAddress("aaa");
        JSONObject externalContact = JSONObject.parseObject(JSONObject.toJSONString(c));
        String aa = (String)externalContact.get("city");
        return StringUtils.isBlank(aa) ? null : aa;
    }

    @Test
    public void test2(){ //536713
        String json = "{\"classId\":91073,\"name\":\"outClassTeacher\",\"operatorId\":800211,\"time\":1657762993095,\"userId\":100000000068498}";
        JSONObject object = JSON.parseObject(json);


        //Map<String,Object> map = JSON.parseObject(json, Map.class);
        Object userId  = object.get("userId");
        System.out.println(Long.valueOf(String.valueOf(userId)));

        //Integer a = 536713;
        //System.out.println(a.longValue());
        //String userId  = map.get("userId");

    }
}

package com.luml.java.data.json.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.luml.domain.City;
import org.apache.commons.lang3.StringUtils;

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
}

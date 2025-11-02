package com.luml.java.data.json;

import org.json.JSONObject;
/**
 * @author luml
 * @description
 *  Java自带的JSON工具类主要包含在org.json包中，包含:JSONObject和JSONArray两个核心类，
 *  用于处理JSON数据的序列化和反序列化操作。
 * @date 2025/11/1
 */
public class JdkOrgJsonTest01 {

    public static void main(String[] args) {

        // 创建空对象并添加数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "John Do");
        jsonObject.put("age", 30);
        System.out.println(jsonObject.toString());

        // 从字符串解析
        String jsonStr = "{\"city\":\"New York\",\"country\":\"USA\"}";
        JSONObject parsedObject = new JSONObject(jsonStr);
        String city = parsedObject.getString("city");
        System.out.println(city);
    }
}

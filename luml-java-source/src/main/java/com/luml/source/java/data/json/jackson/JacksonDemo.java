package com.luml.source.java.data.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luml.source.domain.User3;

public class JacksonDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // 1. 序列化：Java 对象 -> JSON 字符串
        User3 user = new User3("Alice", 25);
        String json = mapper.writeValueAsString(user);
        System.out.println(json); // 输出: {"name":"Alice","age":25}

        // 2. 反序列化：JSON 字符串 -> Java 对象
        String jsonString = "{\"name\":\"Bob\",\"age\":30}";
        User3 userFromJson = mapper.readValue(jsonString, User3.class);
        System.out.println(userFromJson.getName()); // 输出: Bob
    }
}

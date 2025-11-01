package com.luml.java.data.json.fastjson.JSONField;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author luml
 * @description
 * @date 2021/3/13 9:28 下午
 */
public class JSONFieldTest {

    public static class A {
        @JSONField(serializeUsing = AValueSerializer.class)
        public int value;
    }

    public static class B {
        @JSONField(serializeUsing = AValueSerializer.class)
        public int value;
    }

    public static class AValueSerializer implements ObjectSerializer {
        @Override
        public void write(JSONSerializer serializer, Object object,
                          Object fieldName, Type fieldType, int features) throws IOException {
            Integer value = (Integer) object;
            String text = value + "元";
            serializer.write(text);
        }
    }

    public static void main(String[] args) {
        A obj = new A();
        obj.value = 100;
        String json = JSON.toJSONString(obj);
        System.out.println(json);
        //{"value":"100元"}
    }
}

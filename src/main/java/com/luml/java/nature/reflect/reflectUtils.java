package com.luml.java.nature.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luml
 * @description
 * @date 2026/4/12
 */
public class reflectUtils {

    /**
     * 使用反射获取对象的所有属性和值
     * @param obj 要获取属性值的对象
     * @return 包含属性名和值的Map
     * @throws IllegalAccessException
     */
    public static Map<String, Object> getFieldsValue(Object obj) throws IllegalAccessException {
        Map<String, Object> fieldMap = new HashMap<>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // 突破private限制
            Object value = field.get(obj);
            fieldMap.put(field.getName(), value);
        }

        return fieldMap;
    }
}

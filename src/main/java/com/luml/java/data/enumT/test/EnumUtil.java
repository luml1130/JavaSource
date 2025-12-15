package com.luml.java.data.enumT.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2025/12/15
 */
public class EnumUtil {


    public static <T extends Enum<T>> List<Map<String, Object>> enumToList(Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(e -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", e.name());
                    map.put("value", e.ordinal());
                    return map;
                })
                .collect(Collectors.toList());
    }

}

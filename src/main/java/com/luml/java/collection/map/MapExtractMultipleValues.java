package com.luml.java.collection.map;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author luml
 * @description
 * @date 2026/4/12
 */
public class MapExtractMultipleValues {
    public static void main(String[] args) {
        // 创建示例Map
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 10);
        map.put("banana", 20);
        map.put("orange", 30);
        map.put("grape", 40);
        map.put("kiwi", 50);

        // 方法1: 使用keySet()和循环获取多个值
        Set<String> keysToGet = new HashSet<>(Arrays.asList("apple", "orange", "kiwi"));
        Map<String, Integer> result1 = new HashMap<>();
        for (String key : keysToGet) {
            if (map.containsKey(key)) {
                result1.put(key, map.get(key));
            }
        }
        System.out.println("方法1结果: " + result1);

        // 方法2: 使用Stream API (Java 8+)
        Map<String, Integer> result2 = keysToGet.stream()
                .filter(map::containsKey)
                .collect(HashMap::new,
                        (m, k) -> m.put(k, map.get(k)),
                        HashMap::putAll);
        System.out.println("方法2结果: " + result2);

        // 方法3: 使用Map的values()方法获取所有值
        Collection<Integer> allValues = map.values();
        System.out.println("所有值: " + allValues);

        // 方法4: 使用entrySet()方法获取键值对
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        System.out.println("所有键值对:");
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}

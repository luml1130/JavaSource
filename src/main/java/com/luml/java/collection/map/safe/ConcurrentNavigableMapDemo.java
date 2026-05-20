package com.luml.java.collection.map.safe;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author luml
 * @description
 * @date 2026/5/20
 */
public class ConcurrentNavigableMapDemo {
    public static void main(String[] args) {
        // 创建 ConcurrentSkipListMap (ConcurrentNavigableMap 的实现)
        ConcurrentNavigableMap<Integer, String> map = new ConcurrentSkipListMap<>();

        // 放入数据
        map.put(1, "One");
        map.put(3, "Three");
        map.put(5, "Five");
        map.put(7, "Seven");
        map.put(9, "Nine");

        // 1. 导航操作
        System.out.println("Floor key of 4: " + map.floorKey(4)); // 输出: 3
        System.out.println("Ceiling key of 4: " + map.ceilingKey(4)); // 输出: 5
        System.out.println("First entry: " + map.firstEntry()); // 输出: 1=One
        System.out.println("Last entry: " + map.lastEntry());   // 输出: 9=Nine

        // 2. 子映射视图 (SubMap)
        // 获取键在 [3, 7] 范围内的子地图 (3包含, 7包含)
        ConcurrentNavigableMap<Integer, String> subMap = map.subMap(3, true, 7, true);
        System.out.println("SubMap (3-7): " + subMap); // 输出: {3=Three, 5=Five, 7=Seven}

        // 对子地图的修改会影响原地图
        subMap.put(6, "Six");
        System.out.println("Original Map after subMap put: " + map);
        // 输出: {1=One, 3=Three, 5=Five, 6=Six, 7=Seven, 9=Nine}

        // 3. 逆序视图
        System.out.println("Descending Map: " + map.descendingMap());
        // 输出: {9=Nine, 7=Seven, 6=Six, 5=Five, 3=Three, 1=One}

        // 4. 线程安全演示 (伪代码)
        // 多个线程可以同时调用 map.put, map.get, map.subMap 等方法而无需额外同步
    }
}

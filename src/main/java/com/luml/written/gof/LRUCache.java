package com.luml.written.gof;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author luml
 * @description
 * 实现简易 LRU 缓存
 *
 * ‌题目‌：运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。
 * ‌思路‌：结合 HashMap（快速查找）和 双向链表（维护顺序）。Java 中也可直接继承 LinkedHashMap。
 *
 * @date 2026/6/8
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        // accessOrder=true 表示按访问顺序排序
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

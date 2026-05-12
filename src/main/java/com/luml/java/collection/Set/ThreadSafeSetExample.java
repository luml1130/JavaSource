package com.luml.java.collection.Set;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeSetExample {

    public static void main(String[] args) {
        // 1. Collections.synchronizedSet
        Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());
        syncSet.add("A");
        syncSet.add("B");
        // 遍历时必须手动同步
        synchronized (syncSet) {
            for (String item : syncSet) {
                System.out.println("SynchronizedSet: " + item);
            }
        }

        // 2. CopyOnWriteArraySet (读多写少)
        CopyOnWriteArraySet<String> cowSet = new CopyOnWriteArraySet<>();
        cowSet.add("X");
        cowSet.add("Y");
        // 遍历无需同步，安全且不会抛异常
        for (String item : cowSet) {
            System.out.println("CopyOnWriteArraySet: " + item);
        }

        // 3. ConcurrentHashMap.newKeySet() (高并发推荐)
        Set<String> concurrentSet = ConcurrentHashMap.newKeySet();
        concurrentSet.add("1");
        concurrentSet.add("2");
        // 遍历弱一致性，无需同步
        for (String item : concurrentSet) {
            System.out.println("ConcurrentHashMap KeySet: " + item);
        }
        
        // 注意：ConcurrentHashMap.newKeySet() 不允许 null
        try {
            concurrentSet.add(null);
        } catch (NullPointerException e) {
            System.out.println("ConcurrentHashMap KeySet does not allow null");
        }
    }
}

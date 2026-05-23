package com.luml.java.collection.queue.util;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author luml
 * @description
 * @date 2026/5/23
 */
public class LinkedListTest {

    /**
     * 使用LinkedList实现队列
     * LinkedList类实现了Queue接口，因此可以直接作为队列使用。
     */
    @Test
    public void test(){
        Queue<Integer> queue = new LinkedList<>();
        // 入队
        queue.offer(1); queue.offer(2);queue.offer(3);
        // 查看队首元素但不移除
        System.out.println("队首元素: " + queue.peek());
        // 出队
        while (!queue.isEmpty()) {
            System.out.println("出队: " + queue.poll());
        }
    }
}

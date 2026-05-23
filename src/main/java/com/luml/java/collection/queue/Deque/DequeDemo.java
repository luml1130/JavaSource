package com.luml.java.collection.queue.Deque;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author luml
 * @description
 * @date 2026/5/23
 */
public class DequeDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingDeque deque = new LinkedBlockingDeque();
        deque.addFirst("1");
        deque.addLast("2");
        String two = (String) deque.takeLast();
        System.out.println(two);
        String one = (String) deque.takeFirst();
        System.out.println(one);
    }
}

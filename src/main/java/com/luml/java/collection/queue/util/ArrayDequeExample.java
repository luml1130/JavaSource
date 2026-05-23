package com.luml.java.collection.queue.util;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author luml
 * @description
 * @date 2026/5/23
 */
public class ArrayDequeExample {

    /**
     * 使用ArrayDeque实现双端队列作为队列使用
     * 虽然ArrayDeque通常用作双端队列（两端都可以进行插入和删除操作），你也可以用它来实现普通队列：
     * @param args
     */
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>();

        // 入队（在ArrayDeque中，add和offer等价）
        queue.offer(1);queue.offer(2);queue.offer(3);

        // 出队（在ArrayDeque中，remove和poll等价）
        while (!queue.isEmpty()) {
            System.out.println("出队: " + queue.poll());
            // 输出可能是1, 2, 3的顺序，因为是FIFO的
        }
    }

    //作为双端队列使用
    @Test
    public void ArrayDequeExample(){
        Deque<String> deque = new ArrayDeque<>();

        // 尾部插入
        deque.addLast("A");
        deque.addLast("B");

        // 头部插入
        deque.addFirst("Start");

        // 当前状态: [Start, A, B]
        System.out.println(deque);

        // 头部移除
        System.out.println("Poll First: " + deque.pollFirst()); // 输出 Start

        // 尾部移除
        System.out.println("Poll Last: " + deque.pollLast());   // 输出 B

        // 查看头部
        System.out.println("Peek First: " + deque.peekFirst()); // 输出 A

    }

    //作为栈（Stack）使用（推荐替代 Stack 类）
    @Test
    public void StackExample(){
        // 使用 ArrayDeque 模拟栈
        Deque<Integer> stack = new ArrayDeque<>();

        // 入栈 (push 等价于 addFirst)
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // 出栈 (pop 等价于 removeFirst)
        System.out.println(stack.pop()); // 30
        System.out.println(stack.pop()); // 20

        // 查看栈顶
        System.out.println(stack.peek()); // 10
    }


}

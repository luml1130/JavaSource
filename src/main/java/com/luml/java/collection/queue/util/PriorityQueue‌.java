package com.luml.java.collection.queue.util;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author luml
 * @description
 * @date 2026/5/23
 */
public class PriorityQueue‌ {

    public static void main(String[] args) {
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        // 入队
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        // 出队（按照优先级顺序）
        while (!priorityQueue.isEmpty()) {
            System.out.println("出队: " + priorityQueue.poll());
            // 输出可能是1, 2, 3的顺序，取决于优先级队列的实现细节
        }
    }

    //默认最小堆（自然排序）
    @Test
    public void MinHeapExample(){
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(10);
        pq.offer(5);
        pq.offer(20);
        pq.offer(1);

        // 输出顺序：1, 5, 10, 20 (从小到大)
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
    }

    //自定义最大堆（通过 Comparator）
    @Test
    public void MaxHeapExample(){
        // 方法1：使用 Lambda
        PriorityQueue<Integer> maxPq = new PriorityQueue<>((a, b) -> b - a);

        // 方法2：使用 Collections.reverseOrder()
        // PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

        maxPq.offer(10);
        maxPq.offer(5);
        maxPq.offer(20);
        maxPq.offer(1);

        // 输出顺序：20, 10, 5, 1 (从大到小)
        while (!maxPq.isEmpty()) {
            System.out.print(maxPq.poll() + " ");
        }
    }

    //自定义对象优先级
    @Test
    public void TaskScheduler(){
        PriorityQueue<Task> tasks = new PriorityQueue<>();

        tasks.offer(new Task("Backup", 5));
        tasks.offer(new Task("Email", 1));
        tasks.offer(new Task("Report", 3));

        // 输出：Email(P:1), Report(P:3), Backup(P:5)
        while (!tasks.isEmpty()) {
            System.out.println(tasks.poll());
        }
    }
}
class Task implements Comparable<Task> {
    String name;
    int priority; // 数字越小优先级越高

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return name + "(P:" + priority + ")";
    }
}
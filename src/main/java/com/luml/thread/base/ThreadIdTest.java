package com.luml.thread.base;

/**
 * @author luml
 * @description
 * @date 2025/10/26
 */
public class ThreadIdTest {
    public static void main(String[] args) {
        Thread runThread = Thread.currentThread();
        System.out.println(runThread.getName() + " id=" + runThread.getId());// main id = 1
    }
}

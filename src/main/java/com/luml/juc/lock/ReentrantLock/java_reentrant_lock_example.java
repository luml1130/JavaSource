package com.luml.juc.lock.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        example.outer(); // 调用 outer 方法
    }

    private void outer() {
        lock.lock(); // 第一次获取锁
        try {
            System.out.println("进入 outer 方法，已获取锁");
            inner(); // 调用内部方法，再次获取锁
        } finally {
            lock.unlock(); // 释放锁
            System.out.println("退出 outer 方法，锁已释放");
        }
    }

    private void inner() {
        lock.lock(); // 第二次获取锁（可重入）
        try {
            System.out.println("进入 inner 方法，已获取锁");
            // 执行一些操作
        } finally {
            lock.unlock(); // 释放锁
            System.out.println("退出 inner 方法，锁已释放");
        }
    }


}

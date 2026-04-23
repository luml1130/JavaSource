package com.luml.juc.lock.StampedLock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author luml
 * @description
 * @date 2026/4/23
 */
public class Point {
    private double x, y;
    private final StampedLock sl = new StampedLock();


    // 写操作：独占写锁
    void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock(); // 获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp); // 释放写锁
        }
    }

    // 读操作：乐观读 + 悲观读回退
    double distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead(); // 尝试乐观读
        double currentX = x, currentY = y;   // 读取数据

        // 验证乐观读期间是否被写入修改
        if (!sl.validate(stamp)) {
            // 验证失败，升级为悲观读锁
            stamp = sl.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp); // 释放悲观读锁
            }
        }
        return Math.hypot(currentX, currentY);
    }
}

package com.luml.juc.lock.keyword.volatileTest;

/**
 * @author luml
 * @description
 * @date 2026/7/11
 */
public class VolatileLongAtomicityDemo {

    // 场景1：不使用 volatile (在32位JVM或特定配置下可能复现问题)
     private static long sharedValue = 0;

    // 场景2：使用 volatile (保证读写原子性，杜绝数据撕裂)
    //private static volatile long sharedValue = 0;

    public static void main(String[] args) throws InterruptedException {
        // 线程1：不断将 sharedValue 设置为 0
        Thread writer1 = new Thread(() -> {
            while (true) {
                sharedValue = 0L;
            }
        }, "Writer-0");

        // 线程2：不断将 sharedValue 设置为 -1 (二进制全为1)
        Thread writer2 = new Thread(() -> {
            while (true) {
                sharedValue = -1L;
            }
        }, "Writer-1");

        writer1.start();
        writer2.start();

        // 主线程：不断读取 sharedValue 并检查合法性
        // 如果 volatile 生效，读取到的值只能是 0 或 -1
        // 如果未加 volatile 且在非原子环境下，可能读到类似 4294967295 (0xFFFFFFFF00000000) 这样的混合值
        long snapshot;
        int count = 0;
        while (count < 10_000_000) { // 限制循环次数防止死循环，实际测试可去掉
            snapshot = sharedValue;

            // 检查是否出现了“撕裂值”
            if (snapshot != 0L && snapshot != -1L) {
                System.out.println("检测到非原子性读取！当前值: " + snapshot);
                System.out.println("十六进制: 0x" + Long.toHexString(snapshot));
                System.exit(0);
            }
            count++;
        }

        System.out.println("测试完成：未检测到数据撕裂，volatile 保障了 long 读写的原子性。");

        // 停止写入线程
        writer1.stop(); // 仅用于演示，生产环境建议使用标志位停止
        writer2.stop();
    }
}

package com.luml.jvm.oom.gc;

/**
 * @author luml
 * @description
 *  假设有一个类重写了 finalize()，并在其中模拟了耗时操作：
 * @date 2026/7/14
 */
public class BadFinalizeExample {
    private byte[] data = new byte[1024 * 1024]; // 1MB 数据

    @Override
    protected void finalize() throws Throwable {
        try {
            // 模拟耗时操作，阻塞 Finalizer 线程
            Thread.sleep(1000);
            System.out.println("Finalizing...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

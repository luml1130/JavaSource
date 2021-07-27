package com.luml.test.juc.Collections;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author luml
 * @description：
 *  DelayedElement 是我所创建的一个 DelayedElement 接口的实现类，
 *  它不在 Java.util.concurrent 包里。你需要自行创建你自己的 Delayed 接口的实现以使用 DelayQueue 类。
 * @date 2020/11/25
 */
public class DelayQueueExample {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue queue = new DelayQueue();
        Delayed element1 = new DelayedElement();
        queue.put(element1);
        Delayed element2 = queue.take();
    }
}

class DelayedElement implements Delayed{

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed other) {
        return 0;
    }
}

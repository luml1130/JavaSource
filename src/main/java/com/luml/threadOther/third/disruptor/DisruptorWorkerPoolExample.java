package com.luml.threadOther.third.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luml
 * @description
 * 案例 2：高性能工作池模式（多消费者并行处理）
 *   当单个消费者处理能力不足时，可以使用 WorkHandler 和 WorkerPool。
 *   这与 JMS 的点对点模型类似：多个消费者监听同一个队列，每条消息只会被其中一个消费者处理，从而实现负载均衡。
 * 1、定义工作处理器（WorkHandler）注意这里实现的是 WorkHandler 而非 EventHandler。
 * 2、配置多消费者工作池
 * @date 2026/5/18
 */
public class DisruptorWorkerPoolExample {
    //2、配置多消费者工作池
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4); // 4个消费者线程
        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<>(
                new LongEventFactory(),
                bufferSize,
                //DaemonThreadFactory.INSTANCE，//这样用会报错？？？？
                Executors.newSingleThreadExecutor()
        );

        // 关键配置：使用 handleEventsWithWorkerPool
        // 创建3个 Worker 实例，Disruptor 会自动将它们放入工作池
        disruptor.handleEventsWithWorkerPool(
                new JournalWorkHandler("Worker-1"),
                new JournalWorkHandler("Worker-2"),
                new JournalWorkHandler("Worker-3")
        );

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // 生产者快速发布大量消息
        for (long l = 0; l < 100; l++) {
            final long value = l;
            ringBuffer.publishEvent((event, sequence) -> {
                event.set(value);
            });
        }

        // 等待一段时间让消费者处理完
        Thread.sleep(2000);

        disruptor.shutdown();
        executor.shutdown();
    }

}

//1、定义工作处理器（WorkHandler）注意这里实现的是 WorkHandler 而非 EventHandler。
class JournalWorkHandler implements WorkHandler<LongEvent> {
    private final String name;

    public JournalWorkHandler(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(LongEvent event) throws Exception {
        System.out.println("Worker [" + name + "] processing: " + event);
        // 模拟耗时处理
        Thread.sleep(10);
    }
}

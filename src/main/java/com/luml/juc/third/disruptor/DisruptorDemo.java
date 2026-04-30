package com.luml.juc.third.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * 代码说明：
 * 1. 定义了LongEvent作为数据载体，LongEventFactory用于预分配内存以减少GC压力。
 * 2. 展示了三种常见模式：单消费者、广播模式（多消费者处理同一事件）和工作池模式（多消费者竞争处理不同事件）。
 * 3. 使用RingBuffer进行事件发布，通过next()获取序列号，填充数据后调用publish()通知消费者，
 *      体现了Disruptor无锁、高性能的特点。
 */
// 1. 定义事件（Event）：承载数据的对象
class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    public long get() {
        return value;
    }

    @Override
    public String toString() {
        return "LongEvent{value=" + value + "}";
    }
}

// 2. 定义事件工厂（EventFactory）：用于预分配事件对象，避免运行时GC
class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}

// 3. 定义事件处理器（EventHandler）：消费者逻辑，处理接收到的事件
class LongEventHandler implements EventHandler<LongEvent> {
    private final String name;

    public LongEventHandler(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("[" + name + "] Thread: " + Thread.currentThread().getName() 
                + ", Sequence: " + sequence 
                + ", Event: " + event);
    }
}

// 4. 主程序：演示单生产者-单消费者、广播模式、工作池模式
public class DisruptorDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Disruptor 示例演示 ===\n");

        // 示例1：单生产者 - 单消费者
        simpleSingleConsumer();

        System.out.println("\n-------------------------\n");

        // 示例2：广播模式（一个事件被多个消费者同时处理）
        broadcastMode();

        System.out.println("\n-------------------------\n");

        // 示例3：工作池模式（多个消费者竞争处理事件，每个事件只被处理一次）
        workerPoolMode();
    }

    /**
     * 示例1：单生产者 - 单消费者
     */
    private static void simpleSingleConsumer() throws InterruptedException {
        int bufferSize = 1024; // 缓冲区大小，必须是2的幂

        // 创建Disruptor实例
        Disruptor<LongEvent> disruptor = new Disruptor<>(
                new LongEventFactory(),
                bufferSize,
                Executors.newSingleThreadExecutor(),
                //Executors.defaultThreadFactory(),
                ProducerType.SINGLE, // 单生产者
                new BlockingWaitStrategy() // 等待策略
        );

        // 注册消费者
        disruptor.handleEventsWith(new LongEventHandler("SingleConsumer"));

        // 启动Disruptor
        disruptor.start();

        // 获取RingBuffer用于发布事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // 发布10个事件
        for (long i = 0; i < 10; i++) {
            long sequence = ringBuffer.next(); // 获取下一个序列号
            try {
                LongEvent event = ringBuffer.get(sequence); // 获取该位置的事件对象
                event.set(i); // 填充数据
            } finally {
                ringBuffer.publish(sequence); // 发布事件，通知消费者
            }
        }

        // 等待片刻让消费者处理完
        Thread.sleep(100);
        
        // 关闭Disruptor
        disruptor.shutdown();
    }

    /**
     * 示例2：广播模式
     * 同一个事件会被所有注册的消费者处理
     */
    private static void broadcastMode() throws InterruptedException {
        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<>(
                new LongEventFactory(),
                bufferSize,
                Executors.newSingleThreadExecutor(),
               // Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new BlockingWaitStrategy()
        );
       /* public Disruptor(EventFactory<T> eventFactory,
             int ringBufferSize, Executor executor,
                ProducerType producerType, WaitStrategy waitStrategy) {
            this(RingBuffer.create(producerType, eventFactory, ringBufferSize, waitStrategy), executor);
        }*/

        // 注册多个消费者，它们都会收到相同的事件（广播）
        disruptor.handleEventsWith(
                new LongEventHandler("BroadcastConsumer1"),
                new LongEventHandler("BroadcastConsumer2")
        );

        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        for (long i = 0; i < 5; i++) {
            long sequence = ringBuffer.next();
            try {
                LongEvent event = ringBuffer.get(sequence);
                event.set(i);
            } finally {
                ringBuffer.publish(sequence);
            }
        }

        Thread.sleep(100);
        disruptor.shutdown();
    }

    /**
     * 示例3：工作池模式（WorkHandler）
     * 多个消费者竞争处理事件，每个事件只被其中一个消费者处理
     * 适用于负载均衡场景
     */
    private static void workerPoolMode() throws InterruptedException {
        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<>(
                new LongEventFactory(),
                bufferSize,
                Executors.newSingleThreadExecutor(),
                //Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new BlockingWaitStrategy()
        );

        // 使用WorkHandler实现工作池模式
        WorkHandler<LongEvent> worker1 = event -> System.out.println("[Worker1] Thread: " + Thread.currentThread().getName() + ", Event: " + event);
        WorkHandler<LongEvent> worker2 = event -> System.out.println("[Worker2] Thread: " + Thread.currentThread().getName() + ", Event: " + event);

        // 创建工作池
        disruptor.handleEventsWithWorkerPool(worker1, worker2);

        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        for (long i = 0; i < 10; i++) {
            long sequence = ringBuffer.next();
            try {
                LongEvent event = ringBuffer.get(sequence);
                event.set(i);
            } finally {
                ringBuffer.publish(sequence);
            }
        }

        Thread.sleep(100);
        disruptor.shutdown();
    }
}

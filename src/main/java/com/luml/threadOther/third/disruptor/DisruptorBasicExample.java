package com.luml.threadOther.third.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luml
 * @description
 * 案例 1：基础入门（单生产者-单消费者）：
 *         这是最简单的使用场景：生产者发送一个长整型数值，消费者打印该数值。
 * 1. 定义事件（Event）
 * 2、定义事件工厂（EventFactory）Disruptor 需要预分配对象，因此需要工厂方法。
 * 3. 定义消费者（EventHandler）
 * 4. 主程序启动与生产消息
 * @date 2026/5/18
 */
public class DisruptorBasicExample {

    public static void main(String[] args) throws InterruptedException {
        // 1. 创建线程池用于执行消费者逻辑
        ExecutorService executor = Executors.newCachedThreadPool();

        // 2. 指定 RingBuffer 大小 (必须是 2 的幂)
        int bufferSize = 1024;

        // 3. 实例化 Disruptor
        // 参数：工厂, 缓冲区大小, 线程工厂, 生产者类型(单/多), 等待策略
        Disruptor<LongEvent_Basic> disruptor = new Disruptor<>(
                new LongEventFactory_Basic(),
                bufferSize,
                //DaemonThreadFactory.INSTANCE, //这样用会报错？？？？
                Executors.newSingleThreadExecutor(),
               // com.lmax.disruptor.ProducerType.SINGLE,
                ProducerType.SINGLE,
                new BlockingWaitStrategy() // 低CPU消耗，适合非极致延迟场景
        );

        // 4. 注册消费者
        disruptor.handleEventsWith(new LongEventHandler_Basic());

        // 5. 启动 Disruptor
        disruptor.start();

        // 6. 获取 RingBuffer 用于发布事件
        RingBuffer<LongEvent_Basic> ringBuffer = disruptor.getRingBuffer();

        // 7. 生产者发布事件
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l < 10; l++) {
            bb.putLong(0, l);
            // publishEvent 是线程安全的，内部处理了序号申请和发布
            ringBuffer.publishEvent((event, sequence, buffer) -> {
                event.set(buffer.getLong(0));
            }, bb);

            // 模拟生产间隔
            Thread.sleep(100);
        }

        // 8. 关闭资源
        disruptor.shutdown();
        executor.shutdown();
    }
}

//1. 定义事件（Event）
class LongEvent_Basic {
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

//2、定义事件工厂（EventFactory）Disruptor 需要预分配对象，因此需要工厂方法。
class LongEventFactory_Basic implements EventFactory<LongEvent_Basic> {
    @Override
    public LongEvent_Basic newInstance() {
        return new LongEvent_Basic();
    }
}
//3. 定义消费者（EventHandler）
class LongEventHandler_Basic implements EventHandler<LongEvent_Basic> {
    @Override
    public void onEvent(LongEvent_Basic event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Thread: " + Thread.currentThread().getName() +
                " | Sequence: " + sequence +
                " | Event: " + event);
    }
}

package com.luml.threadPool.forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @author luml
 * @description：
 * Java 8 的 ‌parallelStream()‌ 底层即基于 ForkJoinPool.commonPool() 实现
 * long sum = LongStream.rangeClosed(1, 1_000_000)
 *                      .parallel()
 *                      .sum(); // 自动使用 ForkJoin 框架
 * @date 2021/7/22 下午9:47
 */
public class Java8CalculateDemo {

    public static void main(String[] args) {
        Instant startTime = Instant.now();

        // 结算值
        long start = 1;
        long end = 100000000000L;
        Long sum = LongStream.range(start, end).parallel().reduce(0L, Long::sum);

        Instant endTime = Instant.now();

        System.out.println("计算总和:"+sum +"耗时时间:" +
                Duration.between(startTime, endTime).toMillis());
        //计算总和:932355974711512064耗时时间:15956
    }

    @Test
    public void test(){
        Instant startTime = Instant.now();
        long sum = //LongStream.rangeClosed(1, 1_000_000)
                LongStream.rangeClosed(1, 100000000000L)
                .parallel()
                .sum(); // 自动使用 ForkJoin 框架
        Instant endTime = Instant.now();
        System.out.println("sum="+sum+",耗时时间:" + Duration.between(startTime, endTime).toMillis());
        //sum=932356074711512064,耗时时间:15890
    }
}

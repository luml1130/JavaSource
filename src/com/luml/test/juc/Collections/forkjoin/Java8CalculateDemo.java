package com.luml.test.juc.Collections.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @author luml
 * @description
 * @date 2021/7/22 下午9:47
 */
public class Java8CalculateDemo {
    public static void main(String[] args) {
        Instant startTime = Instant.now();
        // 结算值
        long start = 1;
        long end = 100000000000L;
        Long sum = LongStream.range(start, end).parallel().reduce(0L, Long::sum);
        System.out.println("计算总和:" + sum);
        Instant endTime = Instant.now();
        System.out.println("耗时时间:" + Duration.between(startTime, endTime).toMillis());
    }
}

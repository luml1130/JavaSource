package com.luml.source.java.javaClass.date.time;

import java.time.Duration;
import java.time.Instant;

/**
 * Duration 表示两个时间点之间的时间间隔，精确到纳秒。
 * Duration 主要用于计算时间差、延迟等操作。
 * 常用工厂方法（一起记）
     * Duration.ofNanos(1000);    //纳秒
     * Duration.ofMillis(1000);   //毫秒
     * Duration.ofSeconds(10);    //秒
     * Duration.ofMinutes(2);     //分钟
     * Duration.ofHours(1);       //小时
     * Duration.ofDays(1);        //天
 * 常用实例方法
     * duration.toMillis();    //转毫秒long
     * duration.toSeconds();   //转秒
     * duration.toMinutes();
     * duration.toHours();
     * duration.toDays();
 * 注意；
 *  1. **Duration 代表时分秒级间隔**（时、分、秒、毫秒）；按年月日用 `Period`，不要搞混
 * 2. 参数是`long`，如果传 int 会自动提升，超出 long 范围会抛异常
 * 3. 支持负数：`Duration.ofMillis(-1000)` 代表过去的时间
 * 4. 不要和`java.util.concurrent.TimeUnit`混用转换出错，`Duration`是 java.time 标准 API
 *
 */
public class DurationTest {

    public static void main(String[] args) {

        // 创建一个持续时间对象，表示 2 小时
        Duration duration = Duration.ofHours(2);
        System.out.println("Duration of 2 hours: " + duration);

        // 创建一个持续时间对象，表示 30 分钟
        Duration duration30Min = Duration.ofMinutes(30);
        System.out.println("Duration of 30 minutes: " + duration30Min);

        // 1000毫秒 = 1秒
        Duration durationMillis = Duration.ofMillis(1000);
        System.out.println(durationMillis.toSeconds()); // 1
        System.out.println(durationMillis.toMillis());  // 1000

        // 计算两个时间点之间的持续时间
        Instant start = Instant.now();
        // 模拟一些操作
        try {
            Thread.sleep(2000); // 睡眠 2 秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();
        Duration elapsed = Duration.between(start, end);
        System.out.println("Elapsed time: " + elapsed.toMillis() + " milliseconds");
    }
}

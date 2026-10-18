package com.luml.source.java.javaClass.date.time;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstantTest {
    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now); // 直接输出UTC，带Z后缀，例：2026-10-17T14:24:00.951912Z
        //Instant 在 Java 里本身就是 UTC，不带时区偏移
    }

    @Test
    public void transformTest(){
        // 毫秒转Instant
        Instant instant = Instant.ofEpochMilli(1728888888000L);
        System.out.println(instant); //2024-10-14T06:54:48Z

        // Instant转毫秒
        long millis = instant.toEpochMilli();
        System.out.println(millis); //1728888888000

        // Instant 转带时区时间
        ZonedDateTime zdt = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("zat="+zdt);
        //zat=2026-10-18T17:21:02.973939+08:00[Asia/Shanghai]

        // Instant -> LocalDateTime（上海时区）
        LocalDateTime local = Instant.now().atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime();
        System.out.println("local=" + local); //local=2026-10-18T17:21:02.975144

        // LocalDateTime -> Instant（必须指定时区！）???这里有疑问
        LocalDateTime ldt = LocalDateTime.now();
        Instant ins = ldt.atZone(ZoneId.of("Asia/Shanghai")).toInstant();
        System.out.println("ins=" + ins); //ins=2026-10-18T09:21:02.975553Z


    }

    @Test
    public void DurationTest(){
        Instant now = Instant.now();
        System.out.println("now="+now); //now=2026-10-18T09:04:16.773928Z
        //1、当前时间 + 3秒
        Instant later = now.plus(Duration.ofMillis(3000));
        System.out.println("later="+later); //later=2026-10-18T09:04:19.773928Z
        Instant instant2 = now.plusSeconds(10);
        System.out.println("instant2="+instant2); //instant2=2026-10-18T09:04:26.773928Z

        //2、 当前时间 - 500毫秒
        Instant before = now.minus(Duration.ofMillis(500));
        System.out.println("before="+before); //before=2026-10-18T09:04:16.273928Z





        //3、 计算两个Instant之间的时间间隔
        Instant start = Instant.now();
        // 模拟一些耗时操作
        try {
            Thread.sleep(2000); // 休眠2秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();

        // 计算持续时间
        Duration duration = Duration.between(start, end);
        System.out.println("Duration in seconds: " + duration.getSeconds()); //Duration in seconds: 2
    }

    @Test
    public void compareTest(){
        Instant instant1 = Instant.now();
        Instant instant2 = instant1.plusSeconds(10);

        // 比较两个Instant
        boolean isBefore = instant1.isBefore(instant2);
        boolean isAfter = instant1.isAfter(instant2);
        boolean isEqual = instant1.equals(instant2);

        System.out.println("instant1 is before instant2: " + isBefore); // true
        System.out.println("instant1 is after instant2: " + isAfter);   // false
        System.out.println("instant1 is equal to instant2: " + isEqual); // false

    }
}

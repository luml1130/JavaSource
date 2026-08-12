package com.luml.java.javaclass.date;
import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;
//-------------------------
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.OffsetDateTime;

public class ZoneTest {

    /**
     *  旧版 API (java.util.TimeZone) 用法
     * 注意‌：SimpleDateFormat 不是线程安全的，
     *  在多线程环境下应避免将其定义为静态变量，或使用 ThreadLocal 包装，建议迁移至 java.time.format.DateTimeFormatter。
     */
    public void oldApiTest(){
        // 获取时区对象
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");

        // 获取默认时区
        TimeZone defaultTz = TimeZone.getDefault();

        // 在格式化日期时设置时区
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(tz);
        String formattedDate = sdf.format(new Date());
    }
    /**
     * 新版api
     */
    public void newApiTest(){
        //1、获取指定时区
        ZoneId shanghaiZone = ZoneId.of("Asia/Shanghai");
        ZoneId newYorkZone = ZoneId.of("America/New_York");
        // 获取 JVM 默认时区
        ZoneId defaultZone = ZoneId.systemDefault();

        //2、获取带时区的当前时间
        // 获取上海当前时间
        ZonedDateTime nowInShanghai = ZonedDateTime.now(shanghaiZone);
        // 获取纽约当前时间
        ZonedDateTime nowInNewYork = ZonedDateTime.now(newYorkZone);

        // 3、时区转换
        //若要将同一时刻从一个时区转换到另一个时区，使用 withZoneSameInstant 方法：
        ZonedDateTime shanghaiTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        // 转换为纽约时间，保持瞬间时间点不变
        ZonedDateTime newYorkTime = shanghaiTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("上海时间: " + shanghaiTime);
        System.out.println("纽约时间: " + newYorkTime);

        //4、处理固定偏移量 (ZoneOffset)
        // 东八区 (+08:00)
        ZoneOffset offset = ZoneOffset.of("+08:00");
        OffsetDateTime offsetTime = OffsetDateTime.now(offset);


    }
}

package com.luml.java.javaclass.date;

import com.luml.java.javaclass.date.javaTimePac.TimeDateUtils;
import com.luml.java.javaclass.date.javaUtilPac.UtilDateUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author luml
 * @description:
 * date默认精度是毫秒，也就是说生成的时间戳就是13位的，有的时间戳默认就是10位的，因为其精度是秒。
 * 问题2：13位时间戳如何转换成10位时间戳
 *      第一种：通过substring方法，将13位的时间戳最后三位数字截取
 *      第二种：将13位时间戳除以1000取整。
 * @date 2021/8/6 下午11:24
 */
public class UseGetTest {


    public static void main(String[] args) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(new Date(UtilDateUtils.getFrontEndOfDay(-1))));

        System.out.println(UtilDateUtils.addDay(new Date(),365).toInstant().toEpochMilli());
    }

    @Test
    public void getStartOrEnd(){

        Long startOfDay =  UtilDateUtils.getStartOfDay();
        System.out.println("Start of day: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startOfDay));
        //Start of day: 2025-12-14 00:00:00

        Long endOfDay =  UtilDateUtils.getEndOfDay();
        System.out.println("End of day: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endOfDay));
        //End of day: 2025-12-14 23:59:59

        //java8 localDate
        //2、使用LocalDate和LocalTime
        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 一天的开始时间（午夜）
        LocalDateTime startOfDay1 = today.atStartOfDay();
        System.out.println(startOfDay1); //2025-12-14T00:00
        System.out.println(today.atTime(LocalTime.MIN)); //2025-12-14T00:00
        // 一天的结束时间（午夜前一秒）
        LocalDateTime endOfDay1 = today.atTime(LocalTime.MAX);
        System.out.println(endOfDay1); //2025-12-14T23:59:59.999999999

        //3、使用java.time.ZoneId指定时区（可选） 需要指定时区时
        // 获取当前日期和时区（例如：UTC）
        LocalDate today2 = LocalDate.now();
        ZoneId zoneId = ZoneId.of("UTC"); // 可以更改为其他时区，例如 "America/New_York"
        // 一天的开始时间（午夜）在指定时区
        ZonedDateTime startOfDayInZone = today2.atStartOfDay(zoneId);
        System.out.println("Start of day in " + zoneId + ": " + startOfDayInZone);
        //Start of day in UTC: 2025-12-14T00:00Z[UTC]

        // 一天的结束时间（午夜前一秒）在指定时区
        ZonedDateTime endOfDayInZone = today2.atTime(LocalTime.MAX).atZone(zoneId);
        System.out.println("End of day in " + zoneId + ": " + endOfDayInZone);
        //End of day in UTC: 2025-12-14T23:59:59.999999999Z[UTC]
    }

    @Test
    public void getYest(){
        LocalDateTime yesterday = LocalDate.now().minusDays(1).atTime(LocalTime.MAX);
        Date yesterdayDate = TimeDateUtils.localDateTime2Date(yesterday);
        String yesterdayDateStr = UtilDateUtils.formatDate(yesterdayDate,"yyyy-MM-dd HH:mm:ss");
        System.out.println(yesterdayDateStr);
    }

    /**
     * 删除日期的毫秒
     */
    @Test
    public void delNano(){

        Date date = new Date(); // 当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0); // 设置为0毫秒
        date = calendar.getTime(); // 获取新的Date对象
        System.out.println(date); //Sun Dec 14 18:40:47 CST 2025


        //从Java 8开始，推荐使用java.time包中的类，例如LocalDateTime，因为它们提供了更清晰和强大的日期时间处理能力。
        Date date2 = new Date(); // 当前时间
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault());
        localDateTime = localDateTime.withNano(0); // 设置为0毫秒（实际上是纳秒，但效果相同）
        date2 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()); // 获取新的Date对象
        System.out.println(date2); //Sun Dec 14 18:40:47 CST 2025

        //从Java 8开始，推荐使用java.time包中的类，例如LocalDateTime，因为它们提供了更清晰和强大的日期时间处理能力。
        Date data3 = new Date(); // 当前时间
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        zonedDateTime = zonedDateTime.withNano(0); // 设置为0毫秒（实际上是纳秒，但效果相同）
        data3 = Date.from(zonedDateTime.toInstant()); // 获取新的Date对象
        System.out.println(data3);//Sun Dec 14 18:40:47 CST 2025

    }



}

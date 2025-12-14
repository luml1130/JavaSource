package com.luml.java.javaclass.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
public class UseTest {


    public static void main(String[] args) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(new Date(DateUtils.getFrontEndOfDay(-1))));

        System.out.println(DateUtils.addDay(new Date(),365).toInstant().toEpochMilli());
    }

    @Test
    public void getYest(){
        LocalDateTime yesterday = LocalDate.now().minusDays(1).atTime(LocalTime.MAX);
        Date yesterdayDate = DateUtils.localDateTime2Date(yesterday);
        String yesterdayDateStr = DateUtils.formatDate(yesterdayDate,"yyyy-MM-dd HH:mm:ss");
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

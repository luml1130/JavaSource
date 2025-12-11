package com.luml.java.javaclass.date;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author luml
 * @description
 * @date 2025/12/8
 */
public class UseEnterTest {

    /**
     * 获取一天的开始时间和结束时间
     */
    @Test
    public void getStartOrEndOfDay(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 获取当前日期
        LocalDate today = LocalDate.now();

        // 一天的开始时间（午夜）
        LocalDateTime startOfDay = today.atStartOfDay();
        System.out.println("Start of day: " + startOfDay); //Start of day: 2025-12-08T00:00

        // 一天的结束时间（午夜前一秒）
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);
        System.out.println("End of day: " + endOfDay); //Start of day: 2025-12-08T00:00

        String startOfDayString = startOfDay.format(formatter);
        System.out.println(startOfDayString); //Start of day: 2025-12-08T00:00

        String endOfDayString = endOfDay.format(formatter);
        System.out.println(endOfDayString); //Start of day: 2025-12-08T00:00


    }
}

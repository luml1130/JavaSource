package com.luml.java.javaclass.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2025/12/8
 */
public class UseCreatTest {

    public static void main(String[] args) {

    }

    @Test
    public void createArray(){

        //1、 创建一个Date数组
        Date[] dates = new Date[3];
        // 填充日期数组
        dates[0] = new Date(0); // 1970年1月1日
        dates[1] = new Date(); // 当前日期和时间
        dates[2] = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24); // 当前时间之后的24小时

        //2、 使用List先存储LocalDate对象，因为原始类型数组不支持泛型（如LocalDate[]），可以使用List作为替代方案
        List<LocalDate> localDates = new ArrayList<>();
        localDates.add(LocalDate.of(2023, 1, 1)); // 2023年1月1日
        localDates.add(LocalDate.now()); // 当前日期
        localDates.add(LocalDate.now().plusDays(1)); // 当前日期之后的第1天
        // 如果确实需要数组，可以转换为数组
        LocalDate[] localDateArray = localDates.toArray(new LocalDate[0]);

        //3、java.time包中的类（如LocalDateTime）
        List<LocalDateTime> localDateTimes = new ArrayList<>();
        localDateTimes.add(LocalDateTime.of(2023, 1, 1, 0, 0)); // 2023年1月1日午夜
        localDateTimes.add(LocalDateTime.now()); // 当前日期和时间
        localDateTimes.add(LocalDateTime.now().plusHours(1)); // 当前时间之后的1小时
        // 转换为数组
        LocalDateTime[] localDateTimeArray = localDateTimes.toArray(new LocalDateTime[0]);


    }
}

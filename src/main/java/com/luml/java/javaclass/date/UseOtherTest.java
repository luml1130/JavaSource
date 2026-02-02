package com.luml.java.javaclass.date;

import com.luml.java.javaclass.date.javaTimePac.TimeDateUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author luml
 * @description
 * @date 2026/2/2
 */
public class UseOtherTest {

    @Test
    public void compareTest(){
        Date time1 = new Date();

        LocalDateTime localDateTime = LocalDate.now().minusDays(10).atTime(LocalTime.MIN);
        //LocalDateTime yesterdayMAX = LocalDate.now().minusDays(1).atTime(LocalTime.MAX);
        //Date[] beginTime = new Date[2];
        Date time2 = TimeDateUtils.localDateTime2Date(localDateTime);
        System.out.println(time1);
        System.out.println(time2);
       // int result =  time1.compareTo(time2);
       // System.out.println(result);

        switch(time1.compareTo(time2)) {
            case 1:
                System.out.println("大于");
                break;
            case 0:
                System.out.println("相等");
                break;
            case -1:
                System.out.println("小于");
                break;
        }
    }
}

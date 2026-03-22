package com.luml.java.javaclass.date;

import com.luml.java.javaclass.date.javaTimePac.TimeDateUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;

/**
 * @author luml
 * @description
 * LocalDateTime常用比较方法‌
     * ‌equals()‌：判断两个 LocalDateTime 对象是否‌完全相等‌（年、月、日、时、分、秒、纳秒都相同）。
     * ‌compareTo()‌：实现 Comparable 接口，返回负数、0 或正数，分别表示当前时间‌早于、等于、晚于‌另一个时间。
     * ‌isBefore() / isAfter()‌：语义清晰，分别判断当前时间是否在另一个时间之前或之后。
     * ‌isEqual()‌：判断两个时间是否相等（与 equals() 功能一致，但更语义化）。
     * ⚠️ 注意：LocalDateTime ‌不包含时区信息‌，因此比较仅基于其内部的日期时间值，适用于同一时区或明确不需要考虑时区的场景。
 *
 * @date 2026/2/2
 */
public class UseOtherTest {

    @Test
    public void compareToTest(){
        Date time1 = new Date();

        LocalDateTime localDateTime = LocalDate.now().minusDays(10).atTime(LocalTime.MIN);
        //LocalDateTime yesterdayMAX = LocalDate.now().minusDays(1).atTime(LocalTime.MAX);
        //Date[] beginTime = new Date[2];
        Date time2 = TimeDateUtils.localDateTime2Date(localDateTime);
        System.out.println(time2);

        System.out.println(time1);
        System.out.println(time2);
       // int result =  time1.compareTo(time2);
       // System.out.println(result);

        // 大小比较
        //int cmp = dt1.compareTo(dt2);
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

    @Test
    public void equalTest(){
        LocalDateTime dt1 = LocalDateTime.of(2023, Month.DECEMBER, 24, 8, 30, 0);
        LocalDateTime dt2 = LocalDateTime.of(2023, Month.DECEMBER, 24, 8, 40, 0);

        // 相等比较
        boolean isEqual = dt1.equals(dt2); // false
        System.out.println("dt1 == dt2: " + isEqual);

        // 大小比较
        int cmp = dt1.compareTo(dt2); // 返回 -1
        System.out.println("dt1.compareTo(dt2): " + cmp);

        boolean isBefore = dt1.isBefore(dt2); // true
        boolean isAfter = dt1.isAfter(dt2);   // false
        System.out.println("dt1 is before dt2: " + isBefore);
        System.out.println("dt1 is after dt2: " + isAfter);
    }

    @Test
    public void beforeAfterTest(){
        LocalDateTime dt1 = LocalDateTime.of(2023, Month.DECEMBER, 24, 8, 30, 0);
        LocalDateTime dt2 = LocalDateTime.of(2023, Month.DECEMBER, 24, 8, 40, 0);

        boolean isBefore = dt1.isBefore(dt2); // true
        boolean isAfter = dt1.isAfter(dt2);   // false

        System.out.println("dt1 is before dt2: " + isBefore);
        System.out.println("dt1 is after dt2: " + isAfter);
    }
}

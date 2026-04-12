package com.luml.java.javaclass.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author luml
 * @description
 * @date 2021/1/23 9:18 下午
 */
public class pk_calendar_test {

    public static final String DATE_FORMAT = "yyyy.MM.dd";

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getAttendanceDate(1632067200000L));

        for (int i = 1; i <= 5; i++) {
            calendar.add(Calendar.DATE,1);
            System.out.println(calendar.getTimeInMillis());
           // DateUtils.formatDate()
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            System.out.println(df.format(new Date(calendar.getTimeInMillis())));
        }


    }

    public static Long getAttendanceDate(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}

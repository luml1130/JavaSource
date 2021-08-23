package com.luml.java.javaclass.date;

import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public final static String DATE_CHINESE_PATTERN = "yyyy-MM";

    public final static String FULL_DATE_CHINESE_PATTERN = "yyyy-mm-dd hh:mm:ss";

    /**
     * 时间戳转时间(10位时间戳)
     * @return
     */
    @Test
    public void  timestampToDate() {
        //long time
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FULL_DATE_CHINESE_PATTERN);
        //long timeLong = Long.valueOf(time);
        //System.out.println(timeLong);
        System.out.println(System.currentTimeMillis());
        //1628263671736   13位的哦
        String dateTime = simpleDateFormat.format(System.currentTimeMillis());
        //String dateTime = simpleDateFormat.format(new Date(timeLong * 1000L));
        System.out.println(dateTime);
    }

    public static Date convertDate(String date, String pattern) {
        try {
            if (StringUtils.isEmpty(pattern) || StringUtils.isEmpty(date)) {
                String msg = "the date or pattern is empty.";
                throw new IllegalArgumentException(msg);
            }
            SimpleDateFormat df = new SimpleDateFormat(pattern.trim());
            return df.parse(date.trim());
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern.trim());
        return format.format(date);
    }

    /**
     * 将长整型数字转换为日期格式的字符串
     */
    public static String convert2String(Long time, String format) {
        if (time != null) {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date date = new Date(time);
            return sf.format(date);
        }
        return "";
    }

    //--------------add
    public static Date addMonth(Date date, int months) {
        if (months == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        return c.getTime();
    }
    public static Date addDay(Date date, int days) {
        if (days == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, days);
        return c.getTime();
    }

    /**
     *
     * @param currentDay 当前月份的某一天
     * @param day (Calendar.DATE 天 Calendar.HOUR 小时 Calendar.MINUTE 分钟 Calendar.MONTH 月)
     *            需要加上的天数或者需要减去的天数，
     *      *  例如：加上10天：(Calendar.DATE,10）减去十天：(Calendar.DATE,-10)
     * @return
     */
    public static Date subOrAddDay(int currentDay,int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(currentDay, day);
        Date startDate = calendar.getTime();
        return startDate;
    }

    public static Date addHour(Date date, int hours) {
        if (hours == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY,hours);
        return c.getTime();
    }

    /**
     * @return 比如2020-12-09 00:00:00
     */
    public static Long getStartOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }


    public static Long getFrontStartOfDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static Long getEndOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        return calendar.getTimeInMillis();
    }

    public static Long getCurrentWeekStartTime() {
        Calendar weekInstance = Calendar.getInstance();
        weekInstance.set(Calendar.DAY_OF_WEEK, 2);
        weekInstance.set(Calendar.HOUR_OF_DAY, 0);
        weekInstance.set(Calendar.MINUTE, 0);
        weekInstance.set(Calendar.SECOND, 0);
        weekInstance.set(Calendar.MILLISECOND, 0);
        return weekInstance.getTimeInMillis();
    }

    public static Long getCurrentWeekEndTime() {
        Calendar weekInstance = Calendar.getInstance();
        weekInstance.set(Calendar.DAY_OF_WEEK, 7);
        weekInstance.set(Calendar.HOUR_OF_DAY, 23);
        weekInstance.add(Calendar.DATE, 1);
        weekInstance.set(Calendar.MINUTE, 59);
        weekInstance.set(Calendar.SECOND, 59);
        weekInstance.set(Calendar.MILLISECOND, 0);
        return weekInstance.getTimeInMillis();
    }

    public static Long getCurrentYearStartTime() {
        Calendar weekInstance = Calendar.getInstance();
        weekInstance.set(Calendar.DAY_OF_YEAR, 1);
        weekInstance.set(Calendar.HOUR_OF_DAY, 0);
        weekInstance.set(Calendar.MINUTE, 0);
        weekInstance.set(Calendar.SECOND, 0);
        weekInstance.set(Calendar.MILLISECOND, 0);
        return weekInstance.getTimeInMillis();
    }

    public static Long getCurrentYearEndTime() {
        Calendar weekInstance = Calendar.getInstance();
        weekInstance.add(Calendar.YEAR, 1);
        weekInstance.set(Calendar.DAY_OF_YEAR, 1);
        weekInstance.add(Calendar.DATE, -1);
        weekInstance.set(Calendar.HOUR_OF_DAY, 23);
        weekInstance.set(Calendar.MINUTE, 59);
        weekInstance.set(Calendar.SECOND, 59);
        weekInstance.set(Calendar.MILLISECOND, 0);
        return weekInstance.getTimeInMillis();
    }

}

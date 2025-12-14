package com.luml.java.javaclass.date.javaUtilPac;

import lombok.SneakyThrows;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author luml
 * @description
 * @date 2025/12/14
 */
public class UtilDateUtils {

    public final static String DATE_CHINESE_PATTERN = "yyyy-MM";

    public final static String FULL_DATE_CHINESE_PATTERN = "yyyy-mm-dd hh:mm:ss";


    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern.trim());
        return format.format(date);
    }

    /**
     * long 类型转换成日期
     */
    public static String longToDate(Long longTime){
        Date date = new Date(longTime);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    public static Date longToDate(long currentTime, String formatType){
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }


    /**
     * currentTime：要转换的long类型的时间 formatType：要转换的string类型的时间格式
     * @param currentTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    /**
     * 将长整型数字转换为日期格式的字符串
     */
    public static String Long2String(Long time, String format) {
        if (time != null) {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date date = new Date(time);
            return sf.format(date);
        }
        return "";
    }

    /**
     * formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * data Date类型的时间
     * @param data
     * @param formatType
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }


    /**
     * strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
     * strTime的时间格式必须要与formatType的时间格式相同
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    @SneakyThrows
    public static Date stringToDate(String strTime, String formatType){
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = formatter.parse(strTime);
        return date;
    }


    /**
     * formatType时间格式
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

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




    /**
     * 获取一天的开始时间
     * @return 比如2020-12-09 00:00:00
     */
    public static Long getStartOfDay() {
       /* Calendar calendar = new GregorianCalendar(); // 使用GregorianCalendar以避免夏令时问题等。
        calendar.setTime(new Date()); // 设置当前日期和时间。
        calendar.set(Calendar.HOUR_OF_DAY, 0); // 设置小时为0（午夜）。
        calendar.set(Calendar.MINUTE, 0); // 设置分钟为0。
        calendar.set(Calendar.SECOND, 0); // 设置秒为0。
        calendar.set(Calendar.MILLISECOND, 0); // 设置毫秒为0。*/

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

       // Date startOfDay = calendar.getTime();  //返回Date类型
        return calendar.getTimeInMillis(); //返回long类型
    }

    /**
     * 获取一天的结束时间
     * @return
     */
    public static Long getEndOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取前N天的 开始时间
     * @param day
     * @return
     */
    public static Long getFrontStartOfDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取前N天的 最后时间
     * @param day
     * @return
     */
    public static Long getFrontEndOfDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,day);
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

}

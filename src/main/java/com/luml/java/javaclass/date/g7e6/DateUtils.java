package com.luml.java.javaclass.date.g7e6;

import com.luml.java.javaclass.date.other.DateStyle;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @Description 常用时间工具类
 * @Author xiashuhao@e6yun.com
 * @Created Date: 2019/3/7 17:05
 * @ClassName DateUtils
 * @Version: 1.0
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);


    public static final int HOUR_MAX_VALUE = 23;

    public static final int MIN_SEC_MAX_VALUE = 59;



    /**
     * 获取默认的日期
     *
     * @return Date
     */
    public static Date getDefaultDate() {
        return getDefaultDate(logger);
    }

    public static Date getDefaultDate(Logger log) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse("1900-01-01 00:00:00");
        } catch (ParseException e) {
            log.error("获取可登录日期时出现异常", e);
            return new Date();
        }
    }

    public static Date cleanDefaultDate(Date date) {
        if (Objects.isNull(date) || date.getTime()<0) {
            return null;
        }
        return date;
    }

    /**
     * 将秒数转化为时分秒的字符串
     *
     * @param pMilliSecond 秒数
     * @return String
     */
    public static String convertMilliSecondToHMS(double pMilliSecond) {
        return convertSecondToHMS(pMilliSecond / 1000L);
    }

    /**
     * 将秒数转化为时分秒的字符串
     *
     * @param pSecond 秒数
     * @return String
     */
    public static String convertSecondToHMS(double pSecond) {
        StringBuilder hms = new StringBuilder();
        int hour = (int) pSecond / 3600;
        hms.append(hour).append("小时");
        int minute = (int) pSecond % 3600 / 60;
        hms.append(minute).append("分钟");
        int second = (int) pSecond % 3600 % 60;
        hms.append(second).append("秒");
        return String.valueOf(hms);
    }
    public static String convertSecondToMS(double pSecond) {
        int minute = (int) pSecond / (3600 * 60);
        int second = (int) pSecond % 3600 % 60;
        String hms = minute + "分钟" +
                second + "秒";
        return String.valueOf(hms);
    }

    public static String convertSecondToDHMS(long seconds) {
        long days = seconds / (24 * 3600);
        seconds %= (24 * 3600);
        long hours = seconds / 3600;
        seconds %= 3600;
        long minutes = seconds / 60;
        seconds %= 60;

        return String.format("%d天%d小时%d分钟%d秒", days, hours, minutes, seconds);
    }

    public static String convertSecondToHM(long seconds) {
        long hours = seconds / 3600;
        seconds %= 3600;
        long minutes = seconds / 60;

        return String.format("%d小时%d分钟", hours, minutes);
    }

    /**
     * @User: zhaoxue6yun.com
     * @Description:秒转换成汉字格式  如果时分秒为0就不显示
     * 举例  0小时 0分钟 20秒 该方法 返回 20秒
     * @Date: 2019/7/31 17:01
     */
    public static String convertSecondToHMSAbridge(double pSecond) {
        StringBuilder hms = new StringBuilder();
        int hour = (int)pSecond / 3600;
        if(hour > 0){
            hms.append(hour).append("小时");
        }
        int minute = (int)pSecond % 3600 / 60;
        if(minute > 0){
            hms.append(minute).append("分钟");
        }
        int second = (int)pSecond % 3600 % 60;
        if(second > 0){
            hms.append(second).append("秒");
        }
        return String.valueOf(hms);
    }


    /**
     * 获取年月日的日期格式化对象
     *
     * @return DateTimeFormatter
     */
    public static DateTimeFormatter getYMDFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }


    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            logger.error("@DateUtils.getSpecifiedDayAfter", e);
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 获取昨天00:00:00时间
     *
     * @param date
     * @return
     */
    public static Date getYesterdayMinTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天00:00:00时间
     *
     * @param date
     * @return
     */
    public static Date getTodayMinTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取今天23:59:59时间
     *
     * @param date
     * @return
     */
    public static Date getTodayMaxTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取60天之前的日期
     *
     * @param date
     * @return
     */
    public static Date getSecondMonthAgoTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -60);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            logger.error("@DateUtils.getSpecifiedDayBefore", e);
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }


    /**
     * localDate转 自定义格式string
     *
     * @param localDate
     * @param dateStyle 例：yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String formatLocalDateToString(LocalDate localDate, DateStyle dateStyle) {
        return formatLocalDateTimeToString(toLocalDateTime(localDate), dateStyle);
    }

    /**
     * Date类型转字符串
     * @param date
     * @param dateStyle
     * @return
     */
    public static String formatDateToString(Date date, DateStyle dateStyle){
        LocalDateTime localDateTime = date2LocalDateTime(date);
        return formatLocalDateTimeToString(localDateTime, dateStyle);
    }

    public static String formatDateCleanDefault(Date date, DateStyle dateStyle) {
        date = cleanDefaultDate(date);
        if (Objects.isNull(date)) {
            return StringUtils.EMPTY;
        }
        return formatDateToString(date, dateStyle);
    }

    /**
     * localDateTime 转 自定义格式string
     *
     * @param localDateTime
     * @param dateStyle     例：yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String formatLocalDateTimeToString(LocalDateTime localDateTime, DateStyle dateStyle) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateStyle.getValue());
            return localDateTime.format(formatter);

        } catch (DateTimeParseException e) {
            logger.error("日期转换异常", e);
        }
        return null;
    }

    /**
     * string 转 LocalDateTime
     *
     * @param dateStr   例："2017-08-11 01:00:00"
     * @param dateStyle 例："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String dateStr, DateStyle dateStyle) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateStyle.getValue());
            return LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            logger.error("类型转换异常", e);
        }
        return null;
    }


    /**
     * 将字符串对象转换成时间
     * @param strDate
     * @return
     */
    public static Date string2date(String strDate, DateStyle dateStyle) {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateStyle.getValue());
            return sdf.parse(strDate);
        } catch (Exception e) {
            logger.error("字符串转化日期出现异常strDate={}", strDate, e);
        }
        return null;
    }

    /**
     * 获取最大时间
     * @param times
     * @return
     */
    public static Date getMaxDate(Date... times) {
        return Arrays.stream(times).max(Date::compareTo).orElse(null);
    }


    /**
     * LocalDate 转 LocalDateTime
     *
     * @param localDate 例："转为0点0分0秒"
     * @return
     */
    public static LocalDateTime toLocalDateTime(LocalDate localDate) {
        return localDate.atTime(0, 0, 0);
    }

    /**
     * LocalDateTime 转 LocalDate
     *
     * @param localDateTime 例：去掉时间部分
     * @return
     */
    public static LocalDate toLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }


    /**
     * 根据时间获取当月有多少天数
     *
     * @param localDate
     * @return
     */
    public static int getLengthOfMonth(LocalDate localDate) {
        return localDate.lengthOfMonth();
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return 1:星期一；2:星期二；3:星期三；4:星期四；5:星期五；6:星期六；7:星期日；
     */
    public static int getWeekOfDate(LocalDate date) {
        return date.getDayOfWeek().getValue();
    }


    /**
     * 根据日期获得星期的枚举
     *
     * @param date
     * @return MONDAY。。。。。。
     */
    public static DayOfWeek getWeekEnumOfDate(LocalDate date) {
        return date.getDayOfWeek();
    }


    /**
     * 计算两个日期LocalDate相差的毫秒数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getDateDiffMillis(LocalDate before, LocalDate after) {
        return ChronoUnit.MILLIS.between(before, after);
    }


    /**
     * 计算两个时间LocalDateTime相差的毫秒数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getTimeDiffMillis(LocalDateTime before, LocalDateTime after) {
        return ChronoUnit.MILLIS.between(before, after);
    }


    /**
     * 计算两个日期LocalDate相差的秒数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getDateDiffSeconds(LocalDate before, LocalDate after) {
        return ChronoUnit.SECONDS.between(before, after);
    }


    /**
     * 计算两个时间LocalDateTime差的秒数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getTimeDiffSeconds(LocalDateTime before, LocalDateTime after) {
        return ChronoUnit.SECONDS.between(before, after);
    }


    /**
     * 计算两个日期LocalDate相差的分钟数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getDateDiffMinutes(LocalDate before, LocalDate after) {
        return ChronoUnit.MINUTES.between(before, after);
    }


    /**
     * 计算两个时间LocalDateTime差的分钟数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getTimeDiffMinutes(LocalDateTime before, LocalDateTime after) {
        return ChronoUnit.MINUTES.between(before, after);
    }

    /**
     * 计算两个日期LocalDate相差的小时数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getDateDiffHours(LocalDate before, LocalDate after) {
        return ChronoUnit.HOURS.between(before, after);
    }


    /**
     * 计算两个时间LocalDateTime差的小时数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getTimeDiffHours(LocalDateTime before, LocalDateTime after) {
        return ChronoUnit.HOURS.between(before, after);
    }


    /**
     * 计算两个日期LocalDate相差的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getDateDiffDay(LocalDate before, LocalDate after) {
        return ChronoUnit.DAYS.between(before, after);
    }


    /**
     * 计算两个时间LocalDateTime相差的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getTimeDiffDay(LocalDateTime before, LocalDateTime after) {
        return ChronoUnit.DAYS.between(before, after);
    }


    /**
     * 计算两个时间LocalDate相差的月数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getDateDiffMonth(LocalDate before, LocalDate after) {
        return ChronoUnit.MONTHS.between(before, after);
    }


    /**
     * 计算两个时间LocalDateTime相差的月数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getTimeDiffMonth(LocalDateTime before, LocalDateTime after) {
        return ChronoUnit.MONTHS.between(before, after);
    }


    /**
     * 计算两个时间LocalDate相差的年数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getDateDiffYear(LocalDate before, LocalDate after) {
        return ChronoUnit.YEARS.between(before, after);
    }

    /**
     * 计算两个时间LocalDateTime相差的年数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getTimeDiffYear(LocalDateTime before, LocalDateTime after) {

        return ChronoUnit.YEARS.between(before, after);
    }


    /**
     * 获取指定日期的当前的日期数
     *
     * @param localDate
     * @return
     */
    public static int getCurrentDayByLocalDate(LocalDate localDate) {
        return localDate.getDayOfMonth();

    }

    /**
     * 获取指定日期的当前的日期数
     *
     * @param localDateTime
     * @return
     */
    public static int getCurrentDayByLocalDate(LocalDateTime localDateTime) {
        return localDateTime.getDayOfMonth();
    }

    /**
     * 获取指定日期的当前的月份数
     *
     * @param localDate
     * @return
     */
    public static int getCurrentMonthByLocalDate(LocalDate localDate) {
        return localDate.getMonthValue();

    }

    /**
     * 获取指定日期的当前的月份数
     *
     * @param localDateTime
     * @return
     */
    public static int getCurrentMonthByLocalDate(LocalDateTime localDateTime) {
        return localDateTime.getMonthValue();

    }


    /**
     * 获取指定日期是全年的第多少天
     *
     * @param localDate
     * @return
     */
    public static int getDaysOfYearByLocalDate(LocalDate localDate) {
        return localDate.getDayOfYear();

    }

    /**
     * 获取指定日期是全年的第多少天
     *
     * @param localDateTime
     * @return
     */
    public static int getDaysOfYearByLocalDate(LocalDateTime localDateTime) {
        return localDateTime.getDayOfYear();

    }


    /**
     * 特定日期的当月第一天
     *
     * @param localDate
     * @return
     */
    public static LocalDate theFirstDayOfMonth(LocalDate localDate) {
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
    }

    /**
     * 特定日期的当月第一天
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime theFirstDayOfMonth(LocalDateTime localDateTime) {

        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), 1, 0, 0, 0);
    }


    /**
     * 特定日期的当月最后一天
     *
     * @param localDate
     * @return
     */
    public static LocalDate theLastDayOfMonth(LocalDate localDate) {
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
    }

    /**
     * 特定日期的当月第一天
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime theLastDayOfMonth(LocalDateTime localDateTime) {

        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 0, 0, 0);
    }


    /**
     * 特定日期的最晚时间
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime theLastTimeOfDay(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.of(23, 59, 59));
    }

    /**
     * 特定日期的最晚时间
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime theLastTimeOfDay(LocalDateTime localDateTime) {
        return LocalDateTime.of(toLocalDate(localDateTime), LocalTime.of(23, 59, 59));
    }


    /**
     * 特定日期的当年第一天
     *
     * @param localDate
     * @return
     */
    public static LocalDate theFirstDayOfYear(LocalDate localDate) {
        return LocalDate.of(localDate.getYear(), 1, 1);

    }

    /**
     * 特定日期的当年第一天
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime theFirstDayOfYear(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.getYear(), 1, 1, 0, 0, 0);
    }


    /**
     * 获取当前时间
     *
     * @return LocalDateTime
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
    }

    /**
     * 获取当前时间
     *
     * @return LocalDateTime
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now(Clock.system(ZoneId.of("Asia/Shanghai")));
    }


    /**
     * 修改日期时间的时间部分
     *
     * @param date
     * @param customTime 必须为"hh:mm:ss"这种格式
     */
    public static LocalDateTime reserveDateCustomTime(LocalDate date, String customTime) {
        String dateStr = date.toString() + " " + customTime;
        return stringToLocalDateTime(dateStr, DateStyle.YYYY_MM_DD_HH_MM_SS);
    }


    /**
     * 增加或减少年/月/周/天/小时/分/秒数
     *
     * @param localDateTime 例：ChronoUnit.DAYS
     * @param chronoUnit
     * @param num
     * @return LocalDateTime
     */
    public static LocalDateTime addTime(LocalDateTime localDateTime, ChronoUnit chronoUnit, int num) {
        return localDateTime.plus(num, chronoUnit);
    }

    /**
     * 增加或减少年/月/周/天/小时/分/秒数
     *
     * @param localDate  例：ChronoUnit.DAYS
     * @param chronoUnit
     * @param num
     * @return LocalDateTime
     */
    public static LocalDate addOrSubTime(LocalDate localDate, ChronoUnit chronoUnit, int num) {
        return localDate.plus(num, chronoUnit);
    }

    /**
     * 增加或减少年/月/周/天/小时/分/秒数
     *
     * @param date   date
     * @param calendarField Calendar.MINUTE
     * @param num num
     * @return Date
     */
    public static Date addOrSubDate(Date date, int calendarField, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendarField, num);
        return calendar.getTime();
    }

    /**
     * 比较两个LocalDateTime是否同一天
     *
     * @param begin
     * @param end
     * @return
     */
    public static boolean isTheSameDay(LocalDateTime begin, LocalDateTime end) {
        return begin.toLocalDate().equals(end.toLocalDate());
    }


    /**
     * 判断当前时间是否在时间范围内
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isTimeInRange(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime now = LocalDateTime.now();
        return (startTime.isBefore(now) && endTime.isAfter(now)) || startTime.isEqual(now) || endTime.isEqual(now);
    }

    /**
     * Date转换为LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * Date转换为LocalDate
     *
     * @param date
     */
    public static LocalDate date2LocalDate(Date date) {
        return date2LocalDateTime(date).toLocalDate();
    }



}

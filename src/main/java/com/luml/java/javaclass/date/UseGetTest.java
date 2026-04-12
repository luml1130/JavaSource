package com.luml.java.javaclass.date;

import com.luml.java.javaclass.date.util.DateTranUtils;
import com.luml.java.javaclass.date.util.UtilDateUtils;
import com.luml.java.javaclass.date.other.DateStyle;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
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
       // SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // System.out.println(dateFormat.format(new Date(UtilDateUtils.getFrontEndOfDay(-1))));

      //  System.out.println(UtilDateUtils.addDay(new Date(),365).toInstant().toEpochMilli());

        Date date = new Date();
        System.out.println(UtilDateUtils.addMinute(date, -30));;
    }


    @Test
    public void addTest(){
        System.out.println(UtilDateUtils.addDay(new Date(), -5));

        //当前时间 + 30分 或 其他
        LocalDateTime date =  LocalDateTime.now();
        LocalDateTime date2 =  UtilDateUtils.addTime(date, ChronoUnit.MINUTES, 30);
        System.out.println(date2);
    }


    @Test
    public void getTest(){
        String date = UtilDateUtils.localDateToString(LocalDate.now());
        System.out.println(date); // 2026-04-07
        LocalDate localDate =  UtilDateUtils.stringToLocalDate("2026-05-31");
        System.out.println(localDate.minusDays(-1).atTime(LocalTime.MIN));

        Date statTime =  DateTranUtils.localDateTime2Date(LocalDateTime.now().minusDays(1));
        Date statTime2 =  DateTranUtils.localDate2Date(LocalDate.now().minusDays(1));
        System.out.println(statTime); //Sat Apr 11 12:50:26 CST 2026
        System.out.println(statTime2);//这个的是分秒为00:00:00  //这个的是分秒为00:00:00

    }

    @Test
    public void clockTest() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        Instant instant = clock.instant();

        Date date = Date.from(instant);
        System.out.println(clock);
        System.out.println(millis);
        System.out.println(instant);
        System.out.println(date);
    }

    /**
     * java获取时区
     */
    @Test
    public void zoneTest(){
        /**1、java8中基本只能通过当前位置所在城市名来获取时区 **/
        //<1> 查看当前的时区
        ZoneId defaultZone = ZoneId.systemDefault();
        System.out.println(defaultZone); //此处打印为时区所在城市Asia/Shanghai
        //<2>查看美国纽约当前的时间
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime shanghaiTime = LocalDateTime.now(america);
        System.out.println(shanghaiTime);//2021-01-24T03:34:53.796

        /**2.使用SimpleDateFormat 来获取Date时区**/
        DateFormat dateFormat = new SimpleDateFormat("Z");
        System.out.println(dateFormat.format(new Date()));//‘z’小写CST；'Z'大写+0800

        /**3、使用lang3中的org.apache.commons.lang3.time函数获取**/
        System.out.println(DateFormatUtils.format(new Date(), "z"));//‘z’小写CST；'Z'大写 +0800
        System.out.println(DateFormatUtils.format(new Date(), "ZZ"));//'zz'小写一样 "ZZ"大写+08:00

        /***4、使用日历类来计算出传入时间所在时区***/
        Calendar cal = Calendar.getInstance();
        int offset = cal.get(Calendar.ZONE_OFFSET);
        cal.add(Calendar.MILLISECOND, -offset);
        Long timeStampUTC = cal.getTimeInMillis();
        Long timeStamp = System.currentTimeMillis();// date.getTime();
        Long timeZone = (timeStamp - timeStampUTC) / (1000 * 3600);
        System.out.println(timeZone.intValue());//8
    }

    @Test
    public void localDateTest(){
        //获取当前年月日
        LocalDate localDate = LocalDate.now();
        //构造指定的年月日
        LocalDate localDate1 = LocalDate.of(2019, 9, 10);
        //获取年、月、日、星期几
        int year = localDate.getYear();
        int year1 = localDate.get(ChronoField.YEAR);
        Month month = localDate.getMonth();
        int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        int day = localDate.getDayOfMonth();
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);
    }

    /**
     * 获取一天的开始时间和结束时间
     *      1、使用Calendar calendar：UtilDateUtils.getStartOfDay()
     *      2、使用LocalDate和LocalTime：
     *              LocalDate.now().atStartOfDay() /atTime(LocalTime.MIN)/atTime(LocalTime.MAX)
     *      3、使用java.time.ZoneId指定时区（可选）
     *              LocalDate today = LocalDate.now();
     *              ZoneId zoneId = ZoneId.of("UTC");
     *              today.atStartOfDay(zoneId);
     *              today.atTime(LocalTime.MIN).atZone(zoneId);
     *              today.atTime(LocalTime.MAX).atZone(zoneId);
     *
     */
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
        Date yesterdayDate = DateTranUtils.localDateTime2Date(yesterday);
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

    //TimeNanos是用于表示时间的纳秒级精度的字段，常见于时间编码和测量场景中。
    @Test
    public void  TimeNanosTest(){
        System.out.println(System.currentTimeMillis());
        //1763563120900  13位
        System.out.println(System.nanoTime());
        //2980026356694   13位
    }

    public static final DateTimeFormatter STANDARD_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Test
    public void tranTest(){
        //转换
        System.out.println(UtilDateUtils.longToDate(1571916920759L));//2019-10-24 19:35:20
        System.out.println(UtilDateUtils.longToDate(1571916920759L,"yyyy-MM-dd HH:mm:ss"));
        //Thu Oct 24 19:35:20 CST 2019

        //stringToLocalDateTime
        String maxTime = LocalDateTime.now().format(STANDARD_DTF);
        LocalDateTime maxTimeOrigin = DateTranUtils.stringToLocalDateTime(maxTime, DateStyle.YYYY_MM_DD_HH_MM_SS);
        System.out.println(maxTimeOrigin);
        System.out.println(maxTimeOrigin.plusDays(5));
        // minTime.plusDays(5).atTime(LocalTime.MIN);
        String minTime = "2026-02-27 16:03:37.0";
        LocalDate minTimeLocal = LocalDate.parse(minTime,STANDARD_DTF);
        System.out.println(minTimeLocal);
    }

    public void before18(){
        //1-SimpleDateFormat
        Date currentTime = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyyMMdd HHmmss");

        String dateString = formatter.format(currentTime);
        System.out.println(dateString); //2025-12-14 18:49:29

        //2、compareTo
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();
		System.out.println(sdf.format(date));

		Date date1 = new Date(System.currentTimeMillis()+1000*60*60*24*10);

		int num = date1.compareTo(date);
		System.out.println(num);
    }



}

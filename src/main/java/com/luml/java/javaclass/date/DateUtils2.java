package com.luml.java.javaclass.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author luml
 * @description
 * @date 2021/1/24 4:40 下午
 */
public class DateUtils2 {

    /**
     * java获取时区
     */
    @Test
    public void testGetZone(){
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
}

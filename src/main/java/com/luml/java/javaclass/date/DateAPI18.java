package com.luml.java.javaclass.date;

import org.junit.Test;

import java.sql.Date;
import java.time.*;
import java.time.temporal.ChronoField;

//Java 8 在包java.time下包含了一组全新的时间日期API。
//新的日期API和开源的Joda-Time库差不多，但又不完全一样，下面的例子展示了这组新API里最重要的一些部分：
public class DateAPI18 {
	public static void main(String[] args) {

	}
	 public static void main2(String[] args) {
		Clock clock = Clock.systemDefaultZone();
		long millis = clock.millis();
		Instant instant = clock.instant();
		java.util.Date date = Date.from(instant);
		System.out.println(clock);
		System.out.println(millis);
		System.out.println(instant);
		System.out.println(date);
	 }

	 @Test
	 public void testLocalDate(){
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
}

package com.luml.jdk18.api;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;

//Java 8 在包java.time下包含了一组全新的时间日期API。
//新的日期API和开源的Joda-Time库差不多，但又不完全一样，下面的例子展示了这组新API里最重要的一些部分：
public class DateAPI {
	 public static void main(String[] args) {
		Clock clock = Clock.systemDefaultZone();
		long millis = clock.millis();
		Instant instant = clock.instant();
		java.util.Date date = Date.from(instant);
		System.out.println(clock);
		System.out.println(millis);
		System.out.println(instant);
		System.out.println(date);
	 }
}

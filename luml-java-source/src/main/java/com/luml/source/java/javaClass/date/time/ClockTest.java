package com.luml.source.java.javaClass.date.time;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;

public class ClockTest {

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

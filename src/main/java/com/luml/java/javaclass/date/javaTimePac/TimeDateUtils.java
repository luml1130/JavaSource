package com.luml.java.javaclass.date.javaTimePac;

import com.luml.java.javaclass.date.other.DateStyle;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

/**
 * @author luml
 * @description
 * @date 2025/12/14
 */
public class TimeDateUtils {

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    //form g7e6
    public static LocalDateTime stringToLocalDateTime(String dateStr, DateStyle dateStyle) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateStyle.getValue());
            return LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException var3) {
            //logger.error("类型转换异常", var3);
            return null;
        }
    }

    public static LocalDateTime addTime(LocalDateTime localDateTime, ChronoUnit chronoUnit, int num) {
        return localDateTime.plus((long)num, chronoUnit);
    }

    public static LocalDate addOrSubTime(LocalDate localDate, ChronoUnit chronoUnit, int num) {
        return localDate.plus((long)num, chronoUnit);
    }

    public static LocalDate date2LocalDate(Date date) {
        return date2LocalDateTime(date).toLocalDate();
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            return null;
        } else {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        }
    }




}

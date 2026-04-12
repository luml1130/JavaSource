package com.luml.java.javaclass.date.util;

import com.luml.java.javaclass.date.other.DateStyle;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Objects;

/**
 * @author luml
 * @description
 * @date 2026/4/12
 */
public class DateTranUtils {

    /**
     * localDateTime转换为Date
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * localDate转换为Date
     * @param localDate
     */
    public static Date localDate2Date(LocalDate localDate) {
        ZonedDateTime zdt = localDate.atStartOfDay(ZoneId.systemDefault());
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

package com.luml.java.javaclass.date.javaTimePac;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author luml
 * @description JDK 1.8
 * @date 2020/10/12
 */
public class pk_localDate_Test {
    public static void main(String[] args) {
       LocalDateTime date =  LocalDateTime.now();
        System.out.println(date.format(DateTimeFormatter.ISO_DATE));
        System.out.println(LocalDateTime.now().plusMonths(3).toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    @Test
    public void test01(){
        LocalDateTime date =  LocalDateTime.now();
        LocalDateTime date2 =  TimeDateUtils.addTime(date, ChronoUnit.MINUTES, 30);
        System.out.println(date2);
    }







}

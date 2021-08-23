package com.luml.java.javaclass.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author luml
 * @description JDK 1.8
 * @date 2020/10/12
 */
public class LocalDateTest {
    public static void main(String[] args) {
       LocalDateTime date =  LocalDateTime.now();
        System.out.println(date.format(DateTimeFormatter.ISO_DATE));
    }

}

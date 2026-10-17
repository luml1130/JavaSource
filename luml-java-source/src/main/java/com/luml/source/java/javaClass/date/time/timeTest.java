package com.luml.source.java.javaClass.date.time;

import java.time.Instant;

public class timeTest {
    public static void main(String[] args) {

        Instant now = Instant.now();
        System.out.println(now); // 直接输出UTC，带Z后缀，例：2026-10-17T14:24:00.951912Z
        //Instant 在 Java 里本身就是 UTC，不带时区偏移

    }
}

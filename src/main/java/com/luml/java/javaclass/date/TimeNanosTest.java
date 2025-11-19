package com.luml.java.javaclass.date;

/**
 * @author luml
 * @description
 *  TimeNanos是用于表示时间的纳秒级精度的字段，常见于时间编码和测量场景中。
 * @date 2025/11/19
 */
public class TimeNanosTest {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        //1763563120900  13位
        System.out.println(System.nanoTime());
        //2980026356694   13位
    }
}

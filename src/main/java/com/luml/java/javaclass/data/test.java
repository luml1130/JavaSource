package com.luml.java.javaclass.data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author luml
 * @description
 * @date 2021/7/31 下午4:44
 */
public class test {
    public static void main(String[] args) {
        /*Integer a =1;
        Integer b = 2;
        System.out.println(b.equals(a));*/
       /* Float openDuration = 6f;
            long deadLine;
            Float days =openDuration* 100;
            Integer   openDurationDay =days.intValue();
            deadLine = LocalDateTime.now().plusDays(openDurationDay).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(deadLine);

        System.out.println(new Date(deadLine));
        Long tiem = 1638879265L;


        System.out.println(new Date(tiem*1000));*/

    }

    public void test(){
        Long a = 1L;
        if(a.equals(1)){
            System.out.println(true);
        }else{
            System.out.println(true);
        }
    }
}

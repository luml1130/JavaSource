package com.luml.java.javaclass.date;

import com.luml.java.javaclass.date.javaUtilPac.UtilDateUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luml
 * @description
 * @date 2025/12/14
 */
public class JavaUtilDateTest {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date e = new Date();
        System.out.println(e.getTime());
        /*Date date = new Date();
		System.out.println(sdf.format(date));

		Date date1 = new Date(System.currentTimeMillis()+1000*60*60*24*10);
		int num = date1.compareTo(date);
		System.out.println(num);*/


    }

    @Test
    public void tranTest(){
        //转换
        System.out.println(UtilDateUtils.longToDate(1571916920759L));//2019-10-24 19:35:20
        System.out.println(UtilDateUtils.longToDate(1571916920759L,"yyyy-MM-dd HH:mm:ss"));
        //Thu Oct 24 19:35:20 CST 2019
    }

    @Test
    public void tranTest01(){
        Date currentTime = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyyMMdd HHmmss");


        String dateString = formatter.format(currentTime);
        System.out.println(dateString); //2025-12-14 18:49:29

    }



}

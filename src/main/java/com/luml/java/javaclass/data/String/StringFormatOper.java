package com.luml.java.javaclass.data.String;

import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2020/11/23
 */
public class StringFormatOper {
    public static void main(String[] args) {
        //System.out.println(formatTest());
        String aa = "早餐，午餐，晚餐，";
        System.out.println(aa.substring(0,aa.lastIndexOf("，")));
    }

    @Test
    public  void formatTest2() {
        String aa ="今日食谱\r\n" +
                "学校：%s\r\n" +
                "名称：韶华\r\n" +
                "电话：170000000000\r\n" +
                "申请开通营养膳食模块，请及时跟进";
        //String aa ="今日食"+"\r\n"+"学校：%s  名称：韶华 电话：170000000000 申请开通营养膳食模块，请及时跟进";
        System.out.println(String.format(aa, "偶也容易偶也容易"));

    }

    /**
     * 如果碰到传过来的字符串中包含%时，将会抛出异常
     *          ：将不需要转化带%号的字符串 前面多加一个%
     * @return
     */
    public static String formatTest(){
        //String aa = String.format("%s修改%s为%s", 1, 2, 3);
        String aa = String.format("（%s%%～%s%%）", 80, 95);
        return aa;
    }

    @Test
    public void test(){
        //String aa = String.format("（%s%%～%s%%）", 80, 95);
        //System.out.println(String.format("%s年%s月","2020","12"));
        System.out.println(String.format("%s/%s","2020","02/03"));
    }
}

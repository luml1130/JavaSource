package com.luml.java.javaclass.data;

import org.junit.jupiter.api.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author luml
 * @description
 * @date 2020/8/25
 */
public class FormatTest {

    public static void main(String[] args) {
        //NumberFormatTest();
    }

    @Test
    public  void DecimalFormatTest(){

        DecimalFormat dfm = new DecimalFormat("0.00");
        System.out.println(dfm.format(200.23 / 100f));//2.00

        double pi = 3.1415927;
        Float bb = 343.235323f;
        //取一位整数
        System.out.println(new DecimalFormat("0").format(pi));//3
        System.out.println(new DecimalFormat("0.00").format(bb)); // 343.24
        //取一位整数和两位小数
        System.out.println(new DecimalFormat("0.00").format(pi));//3.14
        //取两位整数和三位小数，整数不足部分以0填补。
        System.out.println(new DecimalFormat("00.000").format(pi));// 03.142
        //取所有整数部分
        //以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("#.##%").format(pi));//314.16%
        long c =299792458;  //光速
        //显示为科学计数法，并取五位小数
        System.out.println(new DecimalFormat("#.#####E0").format(c));//2.99792E8
        //显示为两位整数的科学计数法，并取四位小数
        System.out.println(new DecimalFormat("00.####E0").format(c));//29.9792E7
        //每三位以逗号进行分隔。
        System.out.println(new DecimalFormat(",###").format(c));//299,792,458
        //将格式嵌入文本
        System.out.println(new DecimalFormat("光速大小为每秒,###米。").format(c));
    }

    public static void NumberFormatTest(){
        //创建 一个整数格式 地区用系统默认的
        //NumberFormat integerNumber = NumberFormat.getIntegerInstance(Locale.getDefault());
        /**
         *
         使用getInstance或getNumberInstance获取正常的数字格式。
         使用getIntegerInstance得到的整数格式。
         使用getCurrencyInstance来获取货币数字格式。
         使用getPercentInstance获取显示百分比的格式。
         */
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        //整数部分不会每隔三个，就会有 " ,"
        numberFormat.setGroupingUsed(false);
        //线程安全的字符串缓冲类
        StringBuffer stringBuffer = new StringBuffer();
        /**
         * 构造参数 是Format子类里面的 自己特有的参数，传入就行
         * 构造 小数部分的，所以开始 beginIndex（）是从小数点 后面算的，
         *  但是0是从整个格式化数字，第一个算起， 包括 之间用于分组的 " ,"
         */
        FieldPosition fieldPosition = new FieldPosition(NumberFormat.FRACTION_FIELD);
        stringBuffer = numberFormat.format(1234.56789, stringBuffer, fieldPosition);
        System.out.println(stringBuffer.toString());//1234.568
        //小数部分， 所以 从5 开始
        System.out.println(fieldPosition.getBeginIndex() + "   " + fieldPosition.getEndIndex());//5   8
        //切割字符串
        System.out.println(stringBuffer.toString().substring(fieldPosition.getBeginIndex()));//568
    }

    public  static  void NumberFormatTest2(){
        //创建一个默认的通用格式
        NumberFormat numberFormat = NumberFormat.getInstance();
        DecimalFormat numberDecimalFormat;
        //捕捉异常，以防强制类型转换出错
        try {
            //强制转换成DecimalFormat
            numberDecimalFormat = (DecimalFormat) numberFormat;
            //保留小数点后面三位，不足的补零,前面整数部分 每隔四位 ，用 “,” 符合隔开
            numberDecimalFormat.applyPattern("#,####.000");
            //设置舍入模式 为DOWN,否则默认的是HALF_EVEN
            numberDecimalFormat.setRoundingMode(RoundingMode.DOWN);
            //设置 要格式化的数 是正数的时候。前面加前缀
            numberDecimalFormat.setPositivePrefix("Prefix  ");
            System.out.println("正数前缀  "+numberDecimalFormat.format(123456.7891));
            //设置 要格式化的数 是正数的时候。后面加后缀
            numberDecimalFormat.setPositiveSuffix("  Suffix");
            System.out.println("正数后缀  "+numberDecimalFormat.format(123456.7891));
            //设置整数部分的最大位数
            numberDecimalFormat.setMaximumIntegerDigits(3);
            System.out.println("整数最大位数 "+numberDecimalFormat.format(123456.7891));
            //设置整数部分最小位数
            numberDecimalFormat.setMinimumIntegerDigits(10);
            System.out.println("整数最小位数 "+numberDecimalFormat.format(123456.7891));
            //设置小数部分的最大位数
            numberDecimalFormat.setMaximumFractionDigits(2);
            System.out.println("小数部分最大位数 "+numberDecimalFormat.format(123.4));
            //设置小数部分的最小位数
            numberDecimalFormat.setMinimumFractionDigits(6);
            System.out.println("小数部分最小位数 "+numberDecimalFormat.format(123.4));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

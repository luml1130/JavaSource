package com.luml.java.javaclass.data;


import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author luml
 * @description
 * @date 2020/9/1
 */
public class doubleTest {
    public static void main(String[] args) {
       // System.out.println(Double.valueOf("233.5"));//233.5
        //effectNum();
       Double a = 334.333d;
       System.out.println(a.intValue()); // 334
        /**可以将double转换为BigDecimal**/
       BigDecimal b = new BigDecimal(a);
        System.out.println(b.doubleValue()); // 334.333
    }

    @Test
    public  void centToYuan(){
        Double cent = 2003273d;
        Double yuan = cent == null ? 0 :cent/100;
        //BigDecimal bd= new BigDecimal(yuan);
        /**设置为小数点后2位不足补齐 format后是string类型**/
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        System.out.println(decimalFormat.format(yuan));
        //Double d = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        //System.out.println((Double) decimalFormat.format(yuan)); //结果是String
    }

    @Test
    public  void double2Float(){
        Double a = 199.6869d;
        Float f = a.intValue()+0f;
        System.out.println(f); //199.0

        double aa = 199.6869d;
        BigDecimal b = new BigDecimal(aa);
        float c = b.floatValue();
        System.out.println(c);
    }

    /**
     * java float--> double
     * 现将float型转换为字符串型，再转换为精度更高的BigDecimal型，再将其转换为double型。
     */
    public static void float2Double(){
        double d = 3.14;
        float f = (float)d;
        System.out.println(f); //输出结果是:3.14;

        float f2 = 127.1f;
        double d2 = f2;
        System.out.println(d2); // 输出结果是：127.0999984741211

        float f3 = 127.1f;
        BigDecimal b = new BigDecimal(String.valueOf(f));
        double d3 = b.doubleValue();
        System.out.println(d3);//3.14

        Float e = 4.2f;
        System.out.println(Double.valueOf(e* 100)/100);
        System.out.println( Double.valueOf(4.2* 100)/100 );
    }

    @Test
    public void string2Double(){
        /**
         * 第一种方法就是创建了一个新的Double对象。
         * Double有一个构造函数，它需要一个String值，并返回一个具有相同值的Double对象。
         * 如果String未表示有效的Double值，则会发生NumberFormatException。
         */
        String toBeDouble = "200.20";
        Double fromString = new Double(toBeDouble);
        System.out.println("fromString=" + fromString);
        /**
         * 第二种方法是使用double类中的parseDouble(String str)
         * 到目前为止，这是我首选的方法，因为它具有更好的可读性，并且是将字符串值转换为double的标准方法。
         * 其中要处理在将无效的双字符串转换为double对象时，可能发生NumberFormatException
         */
        String toBeDouble2 = "200.20";
        Double doubleString = Double.parseDouble(toBeDouble2);
        System.out.println("doubleString=" + doubleString);
        /**
         * 第三种方法是使用Double.valueOf（String str）方法。
         * 只需将双字符串传递给此方法，就转换为等效的Double值。
         * 如果String为null或不可转换为double，则此方法也可以抛出NumberFormatException。
         */
       /* String toBeDouble3 = "200.20";
        Double doubleStr =(toBeDouble3);
        System.out.println("doubleStr=" + doubleStr);*/

        /**
         * 第一种方法是使用串联运算符 “+”生成一个新字符串
         * 迄今为止最简单的将双重对象转换为字符串的方法。
         */
        Double toBeString = 400.40;
        String fromDouble = "" + toBeString;
        System.out.println("fromDouble=" + fromDouble);
        //第二种方法是使用String.valueOf（double d）方法
        Double toBeString2 = 400.40;
        String strDouble = String.valueOf(toBeString2);
        System.out.println("strDouble=" + strDouble);
        /**
         * 第四种方式更灵活地从Double获取String。它使用String.format（）方法并返回一个格式化的字符串，
         * 这样就可以控制精度级别，并根据需要获得最多两个或三个小数点的字符串。
         */
        Double toBeString3 = 400.40;
        String convertedString = String.format("%.3f",toBeString3);
        System.out.println("convertedString=" + convertedString);








    }

    /**
     * 获取double类型的有效数字
     */
    private static void effectNum(){
        //System.out.println(new BigDecimal(d.toString()).stripTrailingZeros().toPlainString());
        String str1 = "1234.01020300";
        String str2 = "1234.01020300";
        //stripTrailingZeros()此方法返回与移除所有尾部零的数值上相等的BigDecimal,需注意toString()是使用科学计数法输出
        String result1 = new BigDecimal(str1).stripTrailingZeros().toString();
        //toPlainString()返回不带指数字段的此 BigDecimal 的字符串表示形式
        String result2 = new BigDecimal(str2).stripTrailingZeros().toPlainString();
        System.out.println(result1);//输出1234.010203
        System.out.println(result2);//输出1234
    }

    /**
     * double 去除多余的0
     * @param d
     * @return
     */
    public static String doubleTrans(double d){
        if(Math.round(d)-d==0){
            return String.valueOf((long)d);
        }
        return String.valueOf(d);
    }

    /**
     * 四舍五入保留2位小数
     * @return
     */
    @Test
    public void formatDouble(){
        double d = 2000d;
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP).stripTrailingZeros();
        double c = bg.doubleValue();
        System.out.println(c);
        /**2000 --->2000.0  format后是String类型*/
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        String e = decimalFormat.format(c);
        System.out.println(e);
    }



    public static double execuetDouble(double d){
        DecimalFormat df=new DecimalFormat("#.#");
        return Double.parseDouble(df.format(d));
    }

    /**
     * java double 运算
     */
    public static void doubleOper(){
        Double a = 10.32d;
        Double b = 3.01d;
        System.out.println(a-b);
    }

    /**
     * 乘法：
     * System.out.println(doubleMultiply(0.85f,55.32d)); // 47.02200131893158
     * @param a
     * @param b
     * @return
     */
    public static Double doubleMultiply(double a,double b){
         return a * b;

    }

    /**
     * java double 减法
     * System.out.println(doubleSub(10.32d,3.01d)); //7.31
     * @param value1
     * @param value2
     * @return
     */
    public static double doubleSub(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.subtract(b2).doubleValue();
    }



    /**
     * java 比较 double
     * 先把他们转成BigDecimal类型，然后在利用BigDecimal中的compareTo方法去比较大小
     */
    public static void compareDouble() {
        Double stock = 2.4d;
        Double count = 2.4d;
        System.out.println(stock>=count); //true

        Double a = 10.320;
        Double b = 3.010;
        System.out.println(a<b); //false
        System.out.println(new BigDecimal(a));//10.32000000000000028421709430404007434844970703125
        System.out.println(new BigDecimal(b));//3.0099999999999997868371792719699442386627197265625
        System.out.println(new BigDecimal(a).compareTo(new BigDecimal(b)));//1

        BigDecimal data1 = new BigDecimal("1");
        BigDecimal data2 = new BigDecimal("0.01");
        if (data1.compareTo(data2) < 0) {
            System.out.println("第二位数大！");
        }
        if (data1.compareTo(data2) == 0) {
            System.out.println("两位数一样大！");
        }
        if (data1.compareTo(data2) > 0) {
            System.out.println("第一位数大！");
        }
    }

    /**
     * float 转double
     */
    public static void doubleCompareFloat(){
        //fload 先转string 再转BigDecimal 再转double 再对比
        Double a2 = 1.9d;
        float b2 = 1.8f;
        BigDecimal c = new BigDecimal(String.valueOf(b2));
        double d = c.doubleValue();
        System.out.println(a2>d);//true

        float c2 =3.2f;
        System.out.println(new BigDecimal(c2));//3.2000000476837158203125
        System.out.println(new BigDecimal(String.valueOf(c2)));//3.2
    }


    public static void main3(String[] args) {
       // Long aa = new Double(divide(49697d,244d,0)).longValue();
        //System.out.println(aa);
        System.out.println(System.currentTimeMillis());

        System.out.println(new Double(390.91).longValue());

        BigDecimal bigDecimal = new BigDecimal(3.9109);
        System.out.println(bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

    }
    public static  Double divide(Double dividend, Double divisor, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(dividend));
        BigDecimal b2 = new BigDecimal(Double.toString(divisor));
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }
}

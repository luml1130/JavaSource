package com.luml.java.javaclass.data;/**
 * @author luml
 * @description
 * @date 2020/8/25
 */

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author luml
 * @description
 * @date 2020/8/25
 */
public class floatTest {

    public static void main(String[] args) {

        float a  = 99.2f;
        System.out.println((int)a);
       /* DecimalFormat df = new DecimalFormat("0.00");
        float aa= 1649.35f;
        System.out.println(df.format(aa / 4.19f));*/

    }

    public static  void zeroTest(){
        float alldb = 0f;
        float allfat = 0f;
        float alltshw = 0f;

        float allnl = 45f;
        float dbrl = 0f;
        float fatrl = 0f;
        float allrl = 0f;
        float tshwrl = 0f;
        dbrl = alldb * 4;
        fatrl = allfat * 9;
        tshwrl = alltshw * 4;
        if (dbrl + fatrl + tshwrl > allnl) {
            allnl = dbrl + fatrl + tshwrl;
        }

        Float zhehe = 0f;

        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format(allnl /100 ));

        System.out.println(df.format(fatrl / allnl * 100));

       // System.out.println(df.format(0 / b * 100));



    }

    /**
     * 小数 运算
     */
    private static void float2(){
        /**整数**/
        Float subScore1 = -11.6f;
        subScore1 +=  Float.valueOf("-2");
        System.out.println(subScore1);
        /**小数*/
        Float subScore = -11.6f;
        // subScore +=  Float.valueOf("-0.8");
        //System.out.println(subScore); //-12.400001
        subScore  = new BigDecimal(subScore.toString()).add(new BigDecimal(Float.valueOf("-0.8"))).floatValue();
        System.out.println(subScore);
    }

    /**
     * float的 加减乘除 运算
     */
    private static void floatOper(){
        DecimalFormat df = new DecimalFormat("#.00");
        //减法
        Float a1 = 100f;
        Float b1 = -17.9f;
        System.out.println(a1-b1);//65.759995
        BigDecimal a1b = new BigDecimal("100");
        BigDecimal b1b = new BigDecimal(b1.toString());
        System.out.println(a1b.add(b1b));
        //System.out.println(a1b.subtract(b1b));
        //加法
       /* Float aa = 4.56f;
        Float bb = 4.26f;
        System.out.println(aa + bb);
        BigDecimal aaB =  new BigDecimal(aa);
        System.out.println(aaB);
        BigDecimal bbB =  new BigDecimal(bb);
        System.out.println(bbB);
        System.out.println(aaB.add(bbB).floatValue());*/
        //除法
        /*System.out.println(2 *  1650 / 100);   //4.0
        float zhrs = 0f;
        System.out.println(Float.valueOf(zhrs) / 100); //0.2
        float srate = (float)92/100;
        System.out.println(srate); //0.92
        srate = Float.parseFloat(df.format(srate));*/
        //乘法
        /*String minRange = "85";
        Double standardIntake = 1175.0d;
        Float a = Float.valueOf(minRange)/100;
        BigDecimal b = new BigDecimal(Float.toString(a));
        System.out.println(b);
        //BigDecimal c = new BigDecimal(5.7);
        BigDecimal c = new BigDecimal(Double.toString(standardIntake));
        System.out.println(c);
        System.out.println(b.multiply(c));*/


    }

    /**
     * 浮点数的表示是不精确的，不能直接比较两个数是否完全相等，
     *  不可将浮点变量用“==”或“！=”与任何数字比较。
     *  比较两个Float的值 可以用comparetTo(), 返回值 0 （表示相等），1（表示大于） 和 -1（表示小于）
     * @param fa
     * @param fb
     * @return
     */
    private static  float floatContrast(float fa,float fb){
        Float a = 16.5f;
        Float b = 16.1f;
        System.out.println(a.compareTo(b));
        /*Float a = 16.5f;
        Float b = 16.5f;
        String c = "1";
        String d = "1";
        //System.out.println("a == b:" + (a == b)); //包装类型间的相等判断应该用equals，而不是'=='
        System.out.println("a.equals(b):" + a.equals(b));
        System.out.println("a.compareTo(b):" + a.compareTo(b));
        System.out.println("c == d:" + (c == d));
        System.out.println("c.equals(d):" + c.equals(d));*/
        return fa = fb;
    }


    /**
     * 根据数值返回百分比
     * @param number
     * @param total
     * @return
     */
    public static float getPercentage(int number, int total) {
        if (total <= 0) {
            total = 1;
        }
        //两位小数
        DecimalFormat df = new DecimalFormat("0.00");
        String proportionStr = df.format((Float.valueOf(number) / Float.valueOf(total)) * 100);
        float proportion = Float.parseFloat(proportionStr);
        return proportion;
    }
}

package com.luml.java.javaclass.data;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

/**
 * @author luml
 * @description
 * @date 2020/12/1
 */
public class BigDecimalTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String aa= "https://xjj-goods.oss-cn-beijing.aliyuncs.com/错误数据列表.xlsx";
        //HttpUriRequest request = new HttpGet(aa);
        System.out.println(URLEncoder.encode(aa,"UTF-8"));

       /* List<String> editionAgents = null;
        for(String s : editionAgents){
            System.out.println(s);
        }*/
        /*Double cent = 20000d;
        Double yuan = cent == null ? 0 :cent/100;*/

    }


    public static void main2(String[] args) {
       // striZero();
        Float subScore = -73.4f;
        BigDecimal b = new BigDecimal(subScore.toString());

        System.out.println(new BigDecimal("100").add(b).stripTrailingZeros().toPlainString());
    }

    @Test
    public void test(){
        int price = 10020203;//单位分
        System.out.println(DataUtils.feeToYuan(String.valueOf(price)));
        //BigDecimal --> double
        BigDecimal pr = new BigDecimal(0.2);
        pr.doubleValue();

        Long a = 10000L;
        BigDecimal p2 = new BigDecimal(a);
        System.out.println(p2);

        String amount = "10000";

        System.out.println(BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString());
    }

    public static String feeToYuan(String amount){
        /**金额为分的格式 */
        if(!amount.matches("\\-?[0-9]+")) {
            return "0";
        }
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();
    }

    /**
     * 12.【强制】禁止使用构造方法 BigDecimal(double)的方式把 double 值转化为 BigDecimal 对象。
     * 说明：BigDecimal(double)存在精度损失风险，在精确计算或值比较的场景中可能会导致业务逻辑异常。
     * 如：BigDecimal g = new BigDecimal(0.1F); 实际的存储值为：0.10000000149
     * 正例：优先推荐入参为 String 的构造方法，或使用 BigDecimal 的 valueOf 方法，此方法内部其实执行了
     * Double 的 toString，而 Double 的 toString 按 double 的实际能表达的精度对尾数进行了截断。
     * BigDecimal recommend1 = new BigDecimal("0.1");
     * BigDecimal recommend2 = BigDecimal.valueOf(0.1);
     */
    @Test
    public void construnct(){
        BigDecimal g = new BigDecimal(0.1F);
        /**0.100000001490116119384765625**/
        System.out.println(g);
        /**0.10000000149011612**/
        System.out.println(g.doubleValue());
        /**0.100000001490116119384765625**/
        System.out.println(g.toString());
        /**0.100000001490116119384765625**/
        System.out.println(g.toPlainString());

        /**
         * 0.1
         */
        float f = 0.1f;
        BigDecimal a = new BigDecimal(String.valueOf(f));
        System.out.println(a);
    }

    /**
     * 格式化小数
     */
    @Test
    public void xiaoshu(){
        BigDecimal price = new BigDecimal("5.5564");
        /**
         * 保留两位小数
         * 没有设置精度类型如果精度缺失会抛出异常
         */
        //price.setScale(2);
        System.out.println(price);
        /**
         * 保留两位小数四舍五入5.56
         * 2.35变成2.4
         */
        BigDecimal b = price.setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(b);

        /**
         * 保留两位小数四舍五入 如果是5则向下舍 5.55
         * 2.35变成2.3
         */
        BigDecimal price3 = new BigDecimal("2.35");
        BigDecimal d = price3.setScale(1,BigDecimal.ROUND_HALF_DOWN);
        System.out.println("ROUND_HALF_DOWN=" + d);


        /**
         * 直接删除多余的小数位，如5.5564会变成5.55
         */
        BigDecimal price2 = new BigDecimal("5.5564");
        BigDecimal c = price2.setScale(2,BigDecimal.ROUND_HALF_DOWN);
        System.out.println(c);
    }

    public static void striZero(){
        System.out.println( new BigDecimal("100.000").toString());
        //去除末尾多余的0
        System.out.println( new BigDecimal("100.000").stripTrailingZeros().toString()); //1E+2
        //如果想要避免输出科学计数法的字符串，我们要用toPlainString()函数代替toString()
        System.out.println( new BigDecimal("100.000").stripTrailingZeros().toPlainString());//10
    }

    /**
     * java double和float 减法
     * System.out.println(doubleFloatSub(0.32d,3.01f)); // -2.69
     * @return
     */
    @Test
    public  void doubleFloatSub() {
        Double a = 0.32d;
        float b = 3.01f;
        BigDecimal b1 = new BigDecimal(Double.toString(a));
        BigDecimal b2 = new BigDecimal(String.valueOf(b));
        double c =  b1.subtract(b2).doubleValue();
        System.out.println(c);
    }

    /**
     * 精确加法
     */
    @Test
    public void add() {
        double value1=1.2345678912311;
        double value2=9.1234567890123;
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        System.out.println(b1.add(b2).doubleValue());
    }

    /**
     * 精确减法
     */
    @Test
    public void sub() {
        double value1=1.2345678912311;
        double value2=9.1234567890123;
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        System.out.println(b1.subtract(b2).doubleValue());
    }

    /**
     * 精确乘法
     */
    @Test
    public void mul() {
        double value1=1.2345678912311;
        double value2=9.1234567890123;
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        System.out.println(b1.multiply(b2).doubleValue());
    }

    // 除法运算默认精度
    private static final int DEF_DIV_SCALE = 10;
    /**
     * 精确除法 使用默认精度
     */
    @Test
    public void div() throws IllegalAccessException {
        double value1=1.2345678912311;
        double value2=9.1234567890123;
        System.out.println(div(value1, value2, DEF_DIV_SCALE));
    }

    /**
     * 四舍五入
     */
    @Test
    public void round() throws IllegalAccessException {
        double v=1.2345678912311;
       //double value2=9.1234567890123;
        double c = div(v,1, DEF_DIV_SCALE);
        System.out.println(c);
    }

    /**
     * 精确除法
     * @param scale 精度
     */
    public static double div(double value1, double value2, int scale) throws IllegalAccessException {
        if(scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        // return b1.divide(b2, scale).doubleValue();
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 比较大小
     */
    @Test
    public void equalTo() {
        double value1=1.2345678912311;
        double value2=9.1234567890123;
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        if(b1 == null || b2 == null) {
            System.out.println(false);
        }
        System.out.println(0 == b1.compareTo(b2));
    }
}

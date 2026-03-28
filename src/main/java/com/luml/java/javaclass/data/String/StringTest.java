package com.luml.java.javaclass.data.String;

import org.junit.Test;

import javax.sound.midi.Soundbank;

/**
 * @author luml
 * @description
 * @date 2021/4/15 10:22 上午
 */
public class StringTest {
    public static void main(String[] args) {
        String aa = "2020-01";
        String[] bb = aa.split("-");
        System.out.println(bb[0]);
        System.out.println(bb[1]);
        String cc = bb[1];
        if(cc.startsWith("0")){
            System.out.println(cc.replace("0",""));
        }
    }

    @Test
    public void test(){
        String a = "222";
        String c = "333";
        System.out.println((a+c).contains("34"));
    }

    @Test
    public void splitTest(){
        String aa = "wx3adc5e712811b719_ovUli0dhqpnzrCmaCh_HlGX925v0";
        System.out.println(aa.split("_")[1]);
        int index = aa.indexOf("_");
        System.out.println(aa.substring(index+1));
    }
    /********************* format方法  *****************/
    /**
     * 常用转换符
     * 转换符决定了参数如何被解析和显示。以下是一些常用的转换符：
     * | 转换符 | 类型 | 说明 |
     * | --- | --- | --- |
     * | %s | 字符串		     | 任意对象都会调用 toString() 转为字符串 |
     * | %d | 整数（十进制） | 常规整数格式化 |
     * | %f | 浮点数                | 默认保留 6 位小数，可通过精度调整 |
     * | %c | 字符                 | 单个字符，参数为 char 或 int（ASCII 码） |
     * | %b | 布尔值             | 非 null / 非 false 则输出 true，否则 false |
     * | %e | 科学计数法     | 浮点数转为科学计数法格式 |
     * | %t | 日期时间        | 配合子转换符使用，如 %tY 表示年份 |
     */
    @Test
    public  void formatTest2() {
        System.out.println(String.format("%s修改%s为%s", 1, 2, 3));
        //System.out.println(String.format("%s年%s月","2020","12"));
        System.out.println(String.format("%s/%s照片","2020","02/03"));
        String aa ="今日食谱\r\n" +
                "学校：%s\r\n" +
                "名称：韶华\r\n" +
                "电话：170000000000\r\n" +
                "申请开通营养膳食模块，请及时跟进";
        //String aa ="今日食"+"\r\n"+"学校：%s  名称：韶华 电话：170000000000 申请开通营养膳食模块，请及时跟进";
       // System.out.println(String.format(aa, "高新一中"));

        //%b
        String url = "/cgi-bin/school/user/list?department_id=%s&fetch_child=%b";
        System.out.println(String.format(url,1,false));

        //%d | 整数（十进制） | 常规整数格式化 |
        System.out.println(String.format("%05d", 122)); //00122

        //%.2f
        String result = String.format("姓名：%s，年龄：%d，成绩：%.2f", "张三", 25, 89.567);
        System.out.println(result);
        // 输出：姓名：张三，年龄：25，成绩：89.57


       // 输出百分号  若需输出字面意义上的百分号 %，应使用 %% 进行转义：
        System.out.println(String.format("分数：%.2f%%", 95.67)); // 输出：分数：95.67%


       // 使用索引 你可以通过索引来指定参数的位置，增强灵活性：
        System.out.println(String.format("%1$s, %2$d, %1$s", "Hello", 123));
        // 输出：Hello, 123, Hello

        //格式化数字 例如，将数字格式化为带千位分隔符的形式
        System.out.println(String.format("%,d", 1234567)); // 输出：1,234,567

        //2、如果碰到传过来的字符串中包含%时，将会抛出异常 ：将不需要转化带%号的字符串 前面多加一个%
        //String bb = ;
        String bb = String.format("（%s%%～%s%%）", 80, 95);
        //System.out.println("bb="+bb); // bb=（80%～95%）
    }


}

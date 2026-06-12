package com.luml.java.javaclass.data.String;

import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luml
 * @description
 * @date 2025/12/14
 */
public class StringOperateTest {

    @Test
    public void trimTest(){
        String input = "  Hello World  ";
        if (input.trim().isEmpty()) {
            System.out.println("输入为空");
        } else {
            System.out.println(input.trim()); // 输出: Hello World
        }
    }

    @Test
    public void lengthTest(){
        String aa = "woshi s  ";
        String bb = "你天内的是谁 ";
        System.out.println(aa.trim().length());
        System.out.println(bb.trim().length());
    }

    @Test
    public void lengthTest2(){
        StringBuffer sb = new StringBuffer();
        sb.append("abcd");
        System.out.println(sb.length());//返回当前length  4
        System.out.println(sb.capacity());//初始化长度 默认16

        StringBuffer stringBuffer = new StringBuffer(10);
        stringBuffer.append("abcd");
        System.out.println(stringBuffer.length());//返回当前length 4
        System.out.println(stringBuffer.capacity());//10  不用扩容

        StringBuffer stringBuffer1 = new StringBuffer(10);
        stringBuffer1.append("abcd");
        System.out.println(stringBuffer1.length());//返回当前length 4
        System.out.println(stringBuffer1.capacity());//10  不用扩容
    }

    @Test
    public  void splitTest2(){
        //substring方法
        System.out.println("2020-03-08".substring(0,4));

        String aa ="三鲜包子[126.0 g]";
        System.out.println(aa.indexOf("["));
        String bb = aa.substring(aa.lastIndexOf('['));
        System.out.println(bb);
        System.out.println(aa.substring(0,aa.lastIndexOf('[')));
        String filePath = "https://xys-test1.oss-cn-shanghai.aliyuncs.com/logistics/20180329-%E6%89%B9%E9%87%8F%E5%AF%BC%E5%85%A5%E6%98%93%E8%80%97%E5%93%81%E6%A8%A1%E6%9D%BF%2820200924104038%29.xlsx?Expires=1601001639&OSSAccessKeyId=LTAIzG2fkU8Yjp0W&Signature=kLGK7gPNWmW%2BE5fed94juJxt0wI%3D";
        System.out.println(filePath.substring(0,filePath.indexOf("?")));

        /**
         *  字符串分割与遍历
         */
        String data = "apple,banana,orange";
        String[] fruits = data.split(","); // 分割
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

    }

    /**
     * String类操作: 切割省市区
     */
    @Test
    public void splitTest(){
        String aa = "早餐，午餐，晚餐，";
        System.out.println(aa.substring(0,aa.lastIndexOf("，")));
        //方法1：使用split方法
        String fullAddress = "广东省 深圳市 南山区";
        String[] addressParts = fullAddress.split(" ");
        System.out.println("addressParts="+addressParts);

        /**
         * 方法2：使用正则表达式
         * 如果你不确定分隔符是什么（例如，分隔符可能是空格或换行符），你可以使用正则表达式来匹配任何空白字符。
         */
         String fullAddress2 = "广东省 深圳市\n南山区"; // 注意这里的换行符
         String[] addressParts2 = fullAddress2.split("\\s+"); // \\s+ 匹配一个或多个空白符
        System.out.println("addressParts2="+addressParts2);


        if (addressParts.length == 3) {
            String province = addressParts[0];
            String city = addressParts[1];
            String district = addressParts[2];

            System.out.println("省份: " + province);
            System.out.println("城市: " + city);
            System.out.println("区域: " + district);
        } else {
            System.out.println("地址格式不正确");
        }

        /**
         * 方法3：使用Pattern和Matcher类（更灵活）
         * 如果你需要更复杂的匹配逻辑，比如处理不规则的地址格式，可以使用Pattern和Matcher类。
         */
        String fullAddress3 = "广东省-深圳市-南山区"; //OK
        Pattern pattern = Pattern.compile("([\\p{L}]+)\\s*[\\-\\s]*\\s*([\\p{L}]+)\\s*[\\-\\s]*\\s*([\\p{L}]+)");
        Matcher matcher = pattern.matcher(fullAddress3);
        String province = matcher.group(1);
        String city = matcher.group(2);
        String district = matcher.group(3);
        System.out.println("3-province="+province);
        System.out.println("3-city="+city);
        System.out.println("3-district="+district);
    }

}

package com.luml.java.javaclass.data.String;

/**
 * @author luml
 * @description
 * @date 2020/9/9
 */
public class splitTest {
    public static void main(String[] args) {
        subStringTest();
    }
    public static void subStringTest(){
        System.out.println("2020-03-08".substring(0,4));
    }
    public static void split(){
        String aa ="三鲜包子[126.0 g]";
        System.out.println(aa.indexOf("["));
        String bb = aa.substring(aa.lastIndexOf('['));
        System.out.println(bb);
        System.out.println(aa.substring(0,aa.lastIndexOf('[')));
        String filePath = "https://xys-test1.oss-cn-shanghai.aliyuncs.com/logistics/20180329-%E6%89%B9%E9%87%8F%E5%AF%BC%E5%85%A5%E6%98%93%E8%80%97%E5%93%81%E6%A8%A1%E6%9D%BF%2820200924104038%29.xlsx?Expires=1601001639&OSSAccessKeyId=LTAIzG2fkU8Yjp0W&Signature=kLGK7gPNWmW%2BE5fed94juJxt0wI%3D";
        System.out.println(filePath.substring(0,filePath.indexOf("?")));
    }
}

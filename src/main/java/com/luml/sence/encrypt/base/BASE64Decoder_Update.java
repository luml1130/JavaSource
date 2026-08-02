package com.luml.sence.encrypt.base;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 *  //从JDK 1.8开始，就提供了java.util.Base64.Decoder和java.util.Base64.Encoder的JDK公共API，可代替sun.misc.BASE64Decoder和sun.misc.BASE64Encoder的JDK内部API。
 *         //byte[] bytes = new BASE64Decoder().decodeBuffer(base64);
 */
public class BASE64Decoder_Update {

    @Test
    public void  test() throws UnsupportedEncodingException {
        System.out.println("-----------------------早期写法----------------------");
        String text = "字串文字";
        byte[] textByte = text.getBytes("UTF-8");
       /* BASE64Encoder encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();

        //编码
        String encodedText = encoder.encode(textByte);*/
        //System.out.println(encodedText);
        //解码
       // System.out.println(new String(decoder.decodeBuffer(encodedText), "UTF-8"));

        /**
         * 与sun.mis c套件和Apache Commons Codec所提供的Base64编解码器来比较的话，Java 8提供的Base64拥有更好的效能。实际测试编码与解码速度的话，Java 8提供的Base64，要比sun.mis c套件提供的还要快至少11倍，比Apache Commons Codec提供的还要快至少3倍。因此在Java上若要使用Base64，这个Java 8底下的java .util套件所提供的Base64类别绝对是首选！
         * https://blog.csdn.net/zhou_kapenter/article/details/62890262
         */
        System.out.println("-----------------------新的写法----------------------");
        byte[] test11 = Base64.getEncoder().encode(textByte);
        System.out.println("test11字符串="+new String(test11, "UTF-8"));
        byte[] bytes11 = Base64.getDecoder().decode(test11);
        System.out.println("test11="+new String(bytes11, "UTF-8"));

        System.out.println("-----------------------apache写法----------------------");
       // String test22 = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(textByte);
        //System.out.println("test22字符串="+test22);
        //byte[] bytes22 = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(test22);
        //System.out.println("test22="+new String(bytes22, "UTF-8"));

        /**
         * 打印效果: 结果一致
         * -----------------------早期写法----------------------
         * 5a2X5Liy5paH5a2X
         * 字串文字
         * -----------------------新的写法----------------------
         * test11字符串=5a2X5Liy5paH5a2X
         * test11=字串文字
         * -----------------------apache写法----------------------
         * test22字符串=5a2X5Liy5paH5a2X
         * test22=字串文字
         */
    }
}

package com.luml.java.tools.Sercuity;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author luml
 * @description
 * @date 2021/12/23
 */
public class BASE64Uem {

    private static final Logger logger = LoggerFactory.getLogger(BASE64Uem.class);

    /**
     * BASE64解码
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.decodeBase64(key);
    }

    /**
     * BASE64解码
     * @param key
     * @return
     * @throws Exception
     */
    public static String decodeBASE64(String key) {
        String str = null;
        try {
            byte[] array = Base64.decodeBase64(key);
            str = new String(array);
        } catch (Exception e) {
            logger.error("Error Detail", e);
        }
        return str;
    }

    /**
     * BASE64解码
     * @return
     * @throws Exception
     */
    public static byte[] decode(byte[] data) {
        return Base64.decodeBase64(data);
    }

    /**
     * BASE64编码
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) {
        return new String(Base64.encodeBase64(key));
    }

    /**
     * BASE64编码
     * @return
     * @throws Exception
     */
    public static byte[] encode(byte[] data) {
        return Base64.encodeBase64(data);
    }

    /**
     * BASE64编码
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String key) {
        return new String(Base64.encodeBase64(key.getBytes()));
    }

    /**
     * <p>
     * 将文件转成base64 字符串
     * </p>
     * @param filePath 文件路径
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String filePath) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(filePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            logger.error("Error Detail");
        }
        return new String(Base64.encodeBase64(data));//返回Base64编码过的字节数组字符串
    }

    /**
     * <p>
     * 将base64字符解码保存文件
     * </p>
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void decoderBase64File(String base64Code, String targetPath) {
        try {
            byte[] buffer = Base64.decodeBase64(base64Code);
            FileOutputStream out = new FileOutputStream(targetPath);
            out.write(buffer);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("Error Detail", e);
        }
    }

    /**
     * <p>
     * 将base64字符保存文本文件
     * </p>
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void toFile(String base64Code, String targetPath) throws Exception {
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    public static void main(String[] args) {
        String str = "0048F8D37B153F6EA2798C323EF4F318A5624A9E0048F8D37B153F6EA2798C323EF4F318A5624A90048F8D37B153F6EA2798C323EF4F318A5624A9";
        System.out.println(encryptBASE64(str));
    }
}

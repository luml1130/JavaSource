package com.luml.sence.encrypt.DES3;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;

/**
 * @Description DES工具类
 * @Author changyandong@e6yun.com
 * @Created Date: 2018/7/17 9:29
 * @ClassName DES3
 * @Version: 1.0
 */
public class DES3 {
    private static final String PASSWORD_CRYPT_KEY = "e6gisgps";
    private static final String IV = "e656#%&@";

    public DES3() {

    }

    /**
     * 加密
     * <p>
     * 密钥，长度必须是8的倍数
     *
     * @return 返回加密后的数据
     * @throws Exception
     */
    public static String encrypt(String message) throws Exception {
        return encrypt(message, PASSWORD_CRYPT_KEY, IV);
    }

    public static String encrypt(String message, String key, String value) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key
                .getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(value.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] b = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(b);
    }

    /**
     * 解密
     * <p>
     * 密钥，长度必须是8的倍数
     *
     * @return 返回解密后的原始数据
     * @throws Exception
     */
    public static String decrypt(String message) throws Exception {
        return decrypt(message,PASSWORD_CRYPT_KEY,IV);
    }

    public static String decrypt(String message, String key, String value) throws Exception {
        //BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytesrc = Base64.decodeBase64(message.getBytes(StandardCharsets.UTF_8));
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key
                .getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(value.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }



}
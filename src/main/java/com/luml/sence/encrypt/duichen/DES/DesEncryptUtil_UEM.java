package com.luml.sence.encrypt.duichen.DES;

import com.luml.sence.encrypt.Base64.BASE64;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

/**
 * @author luml
 * @description
 * @date 2026/5/6
 */
public class DesEncryptUtil_UEM {
    /**
     * 默认的密钥
     */
    private static final byte[] SECRET_KEY_NORMAL = digest("nq");

    public static void main(String[] args) throws Exception {
        System.out.print(encrypt("123456"));
        System.out.print(decrypt("RJKctynh0Ps="));
    }

    /**
     * MD5加密
     */
    public static byte[] digest(String text) {
        try {
            byte[] b = MessageDigest.getInstance("md5").digest(text.getBytes());
            return b;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串加密
     */
    public static String encrypt(String value) {
        if (value == null)
            return null;
        byte[] bytes = value.getBytes();

        try {
            int keySpilt = SECRET_KEY_NORMAL.length / 2;
            byte[] key = new byte[keySpilt];
            byte[] iv = new byte[keySpilt];
            System.arraycopy(SECRET_KEY_NORMAL, 0, key, 0, keySpilt);
            System.arraycopy(SECRET_KEY_NORMAL, keySpilt, iv, 0, keySpilt);

            SecretKey securekey = SecretKeyFactory.getInstance("DES")
                    .generateSecret(new DESKeySpec(key));
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
            cipher.init(Cipher.ENCRYPT_MODE, securekey, ivParameterSpec);// 设置工作模式为加密模式，给出密钥和向量
            bytes = cipher.doFinal(bytes);

        } catch (Exception e) {
            //Log.e("Utils>>encrypt", "des failer:", e);
            return "";
        }
        return new String(BASE64.encryptBASE64(bytes));
    }

    /**
     * 字符串解密
     * @throws Exception
     */
    public static String decrypt(String encValue) throws Exception {
        if (!StringUtils.hasText(encValue))
            return "";
        byte[] bytes = BASE64.decryptBASE64(encValue);
        if (bytes == null)
            return "";

        try {
            int keySpilt = SECRET_KEY_NORMAL.length / 2;
            byte[] key = new byte[keySpilt];
            byte[] iv = new byte[keySpilt];
            System.arraycopy(SECRET_KEY_NORMAL, 0, key, 0, keySpilt);
            System.arraycopy(SECRET_KEY_NORMAL, keySpilt, iv, 0, keySpilt);

            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            SecretKey securekey = SecretKeyFactory.getInstance("DES")
                    .generateSecret(new DESKeySpec(key));

            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, securekey, ivParameterSpec);
            bytes = cipher.doFinal(bytes);
            if (bytes == null)
                return "";
            return new String(bytes, "UTF-8");
        } catch (Exception e) {
            //Log.e("Utils>>decrypt", "des failer:", e);
            return "";
        }
    }
}

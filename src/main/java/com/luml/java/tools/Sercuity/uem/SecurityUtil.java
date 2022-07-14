package com.luml.java.tools.Sercuity.uem;

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
 * @date 2022/6/26
 */
public class SecurityUtil {

    public static final String SELF_USER_PASSWORD_SUFFIX = "^Sky"; 							//密码后缀
    public static final String SELF_USER_PASSWORD_PREFIX = "NQ_";  							//密码前缀

    /**
     * 默认的密钥
     */
    private static final byte[] SECRET_KEY_NORMAL = digest("nq");

    public static String encryptPasswordWithMD5(String password) {
        return MD5.md5(SELF_USER_PASSWORD_PREFIX + password +
                SELF_USER_PASSWORD_SUFFIX);
    }

    /**
     * 对称加密 密码
     * @param password
     * @return
     */
    public static String encryptPassword(String password) {
        return encrypt(password);
    }

    /**
     * 解密密码
     * @param password
     * @return
     */
    public static String decryptPassword(String password) {
        return decrypt(password);
    }

    /**
     * 字符串加密
     */
    public static String encrypt(String value) {
        if (value == null) {
            return null;
        }
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
     * 字符串解密
     * @throws Exception
     */
    public static String decrypt(String encValue) {
        if (!StringUtils.hasText(encValue)) {
            return "";
        }
        try {
            byte[] bytes = BASE64.decryptBASE64(encValue);
            if (bytes == null) {
                return "";
            }

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
            if (bytes == null) {
                return "";
            }
            return new String(bytes, "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }
}

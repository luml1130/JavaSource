package com.luml.sence.encrypt.duichen.AES;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 使用 AES/CBC/PKCS5Padding 模式，确保数据块对齐和安全性。
 * 每次加密生成随机的 IV，并将其与密文拼接后一起 Base64 编码，方便传输和解密时提取。
 * 密钥通过 KeyGenerator 安全生成，支持 128/192/256 位长度。
 */
public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 128; // 密钥长度：128, 192, 256

    /**
     * 生成随机密钥
     */
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(KEY_SIZE);
        return keyGenerator.generateKey();
    }

    /**
     * 生成随机初始化向量 (IV)
     */
    public static byte[] generateIv() {
        byte[] iv = new byte[16]; // AES block size is 16 bytes
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    /**
     * 加密
     *
     * @param plainText 明文
     * @param key       密钥
     * @param iv        初始化向量
     * @return Base64编码的密文 (IV + Ciphertext)
     */
    public static String encrypt(String plainText, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        
        // 将 IV 和密文组合在一起，以便解密时使用
        byte[] combined = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);
        
        return Base64.getEncoder().encodeToString(combined);
    }

    /**
     * 解密
     *
     * @param encryptedText Base64编码的密文 (IV + Ciphertext)
     * @param key           密钥
     * @return 明文
     */
    public static String decrypt(String encryptedText, SecretKey key) throws Exception {
        byte[] combined = Base64.getDecoder().decode(encryptedText);
        
        // 提取 IV
        byte[] iv = new byte[16];
        System.arraycopy(combined, 0, iv, 0, iv.length);
        
        // 提取密文
        byte[] ciphertext = new byte[combined.length - iv.length];
        System.arraycopy(combined, iv.length, ciphertext, 0, ciphertext.length);
        
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes, "UTF-8");
    }

    public static void main(String[] args) {
        try {
            String originalText = "Hello, AES Encryption!";
            System.out.println("原始文本: " + originalText);

            // 生成密钥和IV
            SecretKey key = generateKey();
            byte[] iv = generateIv();

            // 加密
            String encryptedText = encrypt(originalText, key, iv);
            System.out.println("加密后 (Base64): " + encryptedText);

            // 解密
            String decryptedText = decrypt(encryptedText, key);
            System.out.println("解密后: " + decryptedText);
            
            // 验证
            System.out.println("是否一致: " + originalText.equals(decryptedText));

        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 原始文本: Hello, AES Encryption!
         * 加密后 (Base64): 0PihIDKc89iccLqcyTkt7PY76tJTPzTQhYtDfJ0082mTs+xpGsC9JIvZVEk7DzvL
         * 解密后: Hello, AES Encryption!
         * 是否一致: true
         */
    }
}

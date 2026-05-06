package com.luml.sence.encrypt.message.Digest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 代码说明：
 * 以下提供 ‌SHA-256 摘要‌ 和 ‌HMAC-SHA256‌ 的 Java 实现示例：
 * 1. sha256Hash 方法使用 MessageDigest 生成无密钥哈希，适用于公开数据的完整性校验。
 * 2. hmacSha256 方法使用 Mac 类和密钥生成认证码，适用于需要防止伪造的场景。
 * 3. 任何对输入数据的微小修改都会导致输出的哈希值或 HMAC 值完全不同，从而实现完整性检测。
 */
public class DataIntegrityUtils {

    /**
     * 使用 SHA-256 计算数据的哈希值（摘要）
     * 用于验证数据完整性，无密钥
     *
     * @param data 原始数据字符串
     * @return Base64 编码的哈希值
     */
    public static String sha256Hash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("SHA-256 calculation failed", e);
        }
    }

    /**
     * 使用 HMAC-SHA256 计算消息认证码
     * 用于验证数据完整性和来源真实性，需要密钥
     *
     * @param data 原始数据字符串
     * @param secretKey 密钥字符串
     * @return Base64 编码的 HMAC 值
     */
    public static String hmacSha256(String data, String secretKey) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
            mac.init(keySpec);
            byte[] hmacBytes = mac.doFinal(data.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (Exception e) {
            throw new RuntimeException("HMAC-SHA256 calculation failed", e);
        }
    }

    public static void main(String[] args) {
        String originalData = "Hello, Integrity Check!";
        String secretKey = "my-secret-key";

        // 1. 测试 SHA-256 摘要
        String hash1 = sha256Hash(originalData);
        System.out.println("原始数据: " + originalData);
        System.out.println("SHA-256 摘要: " + hash1);

        // 模拟数据被篡改
        String tamperedData = "Hello, Integrity Check! "; // 末尾加空格
        String hash2 = sha256Hash(tamperedData);
        System.out.println("篡改后数据: " + tamperedData);
        System.out.println("篡改后摘要: " + hash2);
        System.out.println("摘要是否一致: " + hash1.equals(hash2)); // 应为 false

        System.out.println("-------------------------");

        // 2. 测试 HMAC-SHA256
        String hmac1 = hmacSha256(originalData, secretKey);
        System.out.println("HMAC-SHA256: " + hmac1);

        // 使用错误密钥验证
        String wrongKey = "wrong-key";
        String hmac2 = hmacSha256(originalData, wrongKey);
        System.out.println("错误密钥 HMAC: " + hmac2);
        System.out.println("HMAC 是否一致: " + hmac1.equals(hmac2)); // 应为 false
    }
}

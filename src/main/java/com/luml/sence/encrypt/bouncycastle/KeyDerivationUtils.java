package com.luml.sence.encrypt.bouncycastle;


import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.util.DigestFactory;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * 密钥派生
 */
public class KeyDerivationUtils {
    /**
     * 迭代轮次
     */
    public static final int DEFAULT_ITERATIONS = 600000;
    /**
     * 密码长度 单位：比特（bits）
     */
    public static final int DEFAULT_KEY_LENGTH = 256;
    /**
     * 盐长度 单位：字节（bytes）
     */
    public static final int DEFAULT_SALT_LENGTH = 16;

    /**
     * 生成随机盐
     *
     * @param saltLength 盐长度 单位：字节（bytes）
     * @return 盐
     */
    public static byte[] generateSalt(int saltLength) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[saltLength];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * 生成十六进制随机盐
     *
     * @return 十六进制盐
     */
    public static String generateSaltHex() {
        byte[] salt = generateSalt(DEFAULT_SALT_LENGTH);
        return Hex.encodeHexString(salt);
    }

    /**
     * 解析十六进制随机盐
     *
     * @param saltHex 十六进制盐
     * @return 字节数组盐
     */
    @SneakyThrows
    private static byte[] parseSaltHex(String saltHex) {
        //TODO  需要 <commons-codec.version>1.15</commons-codec.version>
        return null;//Hex.decodeHex(saltHex);
    }

    /**
     * @param password   原始密码
     * @param salt       盐
     * @param iterations 迭代轮次
     * @param keyLength  密码长度 单位：比特（bits）
     * @return 密码
     */
    public static byte[] generateKey(byte[] password, byte[] salt, int iterations, int keyLength) {
        PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator(DigestFactory.createSHA256());
        generator.init(password, salt, iterations);
        KeyParameter keyParameter = (KeyParameter) generator.generateDerivedParameters(keyLength);
        return keyParameter.getKey();
    }

    /**
     * 生成十六进制KEY
     *
     * @param password 密码
     * @param salt     十六进制盐
     * @return 十六进制KEY
     */
    public static String generateKeyHex(String password, String salt) {
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        byte[] saltBytes = parseSaltHex(salt);
        byte[] key = generateKey(passwordBytes, saltBytes, DEFAULT_ITERATIONS, DEFAULT_KEY_LENGTH);
        return Hex.encodeHexString(key);
    }

    /**
     * 校验十六进制KEY
     *
     * @param password 密码
     * @param salt     十六进制盐
     * @param key      十六进制KEY
     * @return 校验成功返回true，否则返回false
     */
    public static boolean verifyKeyHex(String password, String salt, String key) {
        return key.equalsIgnoreCase(generateKeyHex(password, salt));
    }
}

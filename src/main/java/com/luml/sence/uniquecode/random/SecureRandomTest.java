package com.luml.sence.uniquecode.random;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author luml
 * @description：
 *  Java 中 SecureRandom 的常见使用示例‌，适用于生成加密安全的随机数，常用于密码、密钥、令牌等高安全性场景。
 * @date 2026/5/6
 */
public class SecureRandomTest {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        // 方法1：使用默认构造（推荐用于大多数场景）
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);

        // 方法2：指定算法（如 SHA1PRNG）
        //‌初始化方式‌：可指定算法，如 SecureRandom.getInstance("SHA1PRNG")，但默认构造通常足够。
        SecureRandom random2 = SecureRandom.getInstance("SHA1PRNG");
        random2.nextBytes(bytes);

        // 方法3：获取强随机数（JDK 8+）
        SecureRandom strong = SecureRandom.getInstanceStrong();


    }

    /**
     * 场景一、基础用法示例
     */
    @Test
    public void baseTest() {
        SecureRandom secureRandom = new SecureRandom();

        // 1. 生成随机整数（int 范围）
        int randomInt = secureRandom.nextInt();
        System.out.println("随机整数: " + randomInt);

        // 2. 生成 0 到 99 之间的随机整数
        int randomInRange = secureRandom.nextInt(100);
        System.out.println("0-99 随机整数: " + randomInRange);

        // 3. 生成随机字节数组（如用于密钥）
        byte[] randomBytes = new byte[16];
        secureRandom.nextBytes(randomBytes);
        System.out.print("随机字节数组: ");
        for (byte b : randomBytes) {
            System.out.printf("%02X ", b);
        }
        System.out.println();

        // 4. 生成随机布尔值
        boolean randomBoolean = secureRandom.nextBoolean();
        System.out.println("随机布尔值: " + randomBoolean);

        // 5. 生成随机双精度浮点数 [0.0, 1.0)
        double randomDouble = secureRandom.nextDouble();
        System.out.println("随机浮点数: " + randomDouble);
    }

    /**
     * 场景二、生成指定长度的随机字符串（如验证码）
     */
    @Test
    public void RandomStringGenerator(){
        System.out.println("6位随机字符串: " + generateRandomString(6));
        //6位随机字符串: PKBXQk
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static String generateRandomString(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    /**
     * 场景三、生成安全密码（含大小写字母、数字、特殊字符）
     */
    @Test
    public  void PasswordGenerator() throws NoSuchAlgorithmException {
       // SecureRandom secureRandom = new SecureRandom();
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()_+-=[]{}|";

        String allChars = uppercase + lowercase + digits + special;
        int passwordLength = 12;
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            int index = secureRandom.nextInt(allChars.length());
            password.append(allChars.charAt(index));
        }

        System.out.println("生成的密码: " + password.toString());
        //生成的密码: lV[uU=QNm1I6
    }
}

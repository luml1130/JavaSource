package com.luml.sence.encrypt.Crypto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 使用Spring Security Crypto模块中的BCryptPasswordEncoder类进行密码加密
 * 通过encode()方法将明文密码转换为不可逆的哈希值，每次加密结果不同
 * 通过matches()方法验证输入密码与已加密密码是否匹配
 * 支持自定义加密强度（cost factor），默认为10，范围4-31
 * 内部自动处理盐值生成和存储，确保相同密码每次生成不同哈希值
 */
public class BCryptExample {
    public static void main(String[] args) {
        // 创建 BCryptPasswordEncoder 实例
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        // 待加密的密码
        String rawPassword = "mySecretPassword123";
        
        // 加密密码
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("原始密码: " + rawPassword);
        System.out.println("加密后密码: " + encodedPassword);
        
        // 验证密码
        boolean isMatch = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("密码匹配: " + isMatch);
        
        // 使用不同强度的 BCryptPasswordEncoder
        BCryptPasswordEncoder strongEncoder = new BCryptPasswordEncoder(12);
        String strongEncoded = strongEncoder.encode(rawPassword);
        System.out.println("高强度加密: " + strongEncoded);
    }
}

package com.luml.sence.encrypt.Crypto;

import org.junit.Test;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

/**
 * @author luml
 * @description
 * @date 2026/4/15
 */
public class SpringSecurityCryptoExample {

    /**
     * 代码说明：
     * 1. 该示例展示了如何使用Spring Security Crypto模块中的Encryptors类来创建TextEncryptor实例。
     * 2. 使用KeyGenerators生成随机盐值用于加密过程。
     * 3. 通过TextEncryptor对文本进行加密和解密操作，体现了模块在文本加密方面的基本功能。
     */
    @Test
    public void SpringSecurityTest(){
        // 生成随机盐值
        String salt = KeyGenerators.string().generateKey();
        System.out.println("Generated Salt: " + salt); //Generated Salt: 6b0d02cd8ac686d3

        // 使用标准加密方法创建TextEncryptor
        TextEncryptor encryptor = Encryptors.text("myPassword", salt);

        // 加密文本
        String plainText = "Hello, Spring Security Crypto!";
        String encryptedText = encryptor.encrypt(plainText);
        System.out.println("Encrypted Text: " + encryptedText);
        //Encrypted Text: 281429d3da35a1a29899d67b87c3db15d8907b3833afd47341f14652b3a89404be8e7b82968acce9a90237be8cecb942

        // 解密文本
        String decryptedText = encryptor.decrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
        //Decrypted Text: Hello, Spring Security Crypto!
    }
}

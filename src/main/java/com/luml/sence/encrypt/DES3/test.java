package com.luml.sence.encrypt.DES3;

import cn.hutool.core.lang.UUID;
import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2026/4/17
 */
public class test {

    /**
     * G7E6 用户token 生成
     * @return
     * @throws Exception
     */
    @Test
    public void genToken() throws Exception {
        Integer userId = 1;
        String uuid = UUID.randomUUID().toString();

        String message = uuid.concat(String.valueOf(userId));
        String encryptStr = DES3.encrypt(message);
        System.out.println(encryptStr);
        //+TXkKwax/ELfWjSzQDEa+9YFGSXi+BEJgl+Z/TqGpBMZdByTV/GZMw==
    }

    @Test
    public void tokenDecrypt() throws Exception {
        String token = "+TXkKwax/ELfWjSzQDEa+9YFGSXi+BEJgl+Z/TqGpBMZdByTV/GZMw==";
        String decrypt = DES3.decrypt(token);

        String userId = decrypt.substring(36);
        System.out.println(userId); //1
    }
}

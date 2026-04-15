package com.luml.sence.encrypt.bouncycastle;

import com.alibaba.fastjson.JSON;

import java.util.Objects;

/**
 * @author luml
 * @description
 *  引入：<dependency>
 *             <groupId>org.bouncycastle</groupId>
 *             <artifactId>bcprov-jdk15to18</artifactId>
 *             <version>${bouncycastle.version}</version>
 *         </dependency>
 *  使用：G7e6-login模块登录接口使用
 * @date 2026/4/15
 */
public class bouncyTest {

    public static void main(String[] args) {
        /*String password = cipherSaltPwdFlag? userPassword : KeyDerivationUtils.generateKeyHex(userPassword, userVO.getSalt());
        if (!Objects.equals(password, userVO.getUserPassword())) {
            log.info("登陆失败详细日志：密码不对。userCode:{};userVO:{}", userCode, JSON.toJSONString(userVO));
            throw new E6yunLoginException(I.n("用户名或密码错误"), loginResultVO);
        }*/
        //正确不传luml1130 前端密码后再给我  后端再二次加密
        String password = KeyDerivationUtils.generateKeyHex("luml1130", "key-admin");
        System.out.println(password);
        //8b54cb7baff0300592444b98b937814358427b75dd9191bed831be3f529261b6
    }
}

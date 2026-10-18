package com.luml.source.sence.rule.Desensitized.hutool;

import cn.hutool.core.util.DesensitizedUtil;

public class DesensitizedUtilTest {
    public static void main(String[] args) {
        // 手机号脱敏：138&zwnj;****&zwnj;8000
        String mobile = DesensitizedUtil.mobilePhone("13800138000");
        System.out.println("脱敏手机号: " + mobile);

        // 身份证脱敏：1&zwnj;***************&zwnj;4
        String idCard = DesensitizedUtil.idCardNum("110101199001011234", 1, 1);
        System.out.println("脱敏身份证: " + idCard);

        // 邮箱脱敏：e&zwnj;****&zwnj;@test.com
        String email = DesensitizedUtil.email("example@test.com");
        System.out.println("脱敏邮箱: " + email);
    }
}

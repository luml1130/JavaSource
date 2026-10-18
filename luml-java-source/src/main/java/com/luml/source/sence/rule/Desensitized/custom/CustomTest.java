package com.luml.source.sence.rule.Desensitized.custom;

public class CustomTest {
    public static void main(String[] args) {
        String maskedPhone = CustomMaskingType.PHONE.mask("13800138000");
        System.out.println(maskedPhone);
    }
}

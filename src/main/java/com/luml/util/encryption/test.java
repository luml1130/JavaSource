package com.luml.util.encryption;

import icu.xuyijie.sm4utils.util.Sm4Utils;

/**
 * @author luml
 * @description
 * @date 2026/1/22
 */
public class test {

    public static void main(String[] args) throws Exception {

        String passWord =  Sm4Utils.encryptData_ECB("lu1160","Dx6QVMjEXuv6btzR");
        System.out.println(passWord);
        //String passWord = "V8Dz3sCOC8r40YpDMSkOHA==";

        String s1 = Sm4Utils.decryptData_ECB(passWord, "Dx6QVMjEXuv6btzR");
        System.out.println(s1); //1130
    }

}

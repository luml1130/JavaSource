package com.luml.java.javaclass.data.String;

import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2021/4/15 10:22 上午
 */
public class StringTest {
    public static void main(String[] args) {
        String aa = "2020-01";
        String[] bb = aa.split("-");
        System.out.println(bb[0]);
        System.out.println(bb[1]);
        String cc = bb[1];
        if(cc.startsWith("0")){
            System.out.println(cc.replace("0",""));
        }
    }

    @Test
    public void test(){
        String a = "222";
        String c = "333";
        System.out.println((a+c).contains("34"));
    }

    @Test
    public void splitTest(){
        String aa = "wx3adc5e712811b719_ovUli0dhqpnzrCmaCh_HlGX925v0";
        System.out.println(aa.split("_")[1]);
        int index = aa.indexOf("_");
        System.out.println(aa.substring(index+1));
    }
}

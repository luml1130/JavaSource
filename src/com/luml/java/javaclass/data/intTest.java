package com.luml.java.javaclass.data;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @author luml
 * @description
 * @date 2021/1/13 10:30 上午
 */
public class intTest {

    public static void main(String[] args) {
        System.out.println(isOdd(8));
    }

    /**
     * 判断是否是奇数
     * @param a
     * @return
     */
    public static boolean isOdd(int a) {
        if ((a & 1) != 0) {
            return true;
        }
        return false;
    }

    @Test
    public void rondom(){
        Random r = new Random(1);
        for(int i=0 ; i<12 ; i++){
            int ran1 = r.nextInt(100);
            System.out.println(ran1);
        }
    }

    @Test
    public void rondom2(){
        int max=12;
        int min=1;
        Random random = new Random();
        for(int i=0 ; i<12 ; i++) {
            int s = random.nextInt(max) % (max - min + 1) + min;
            System.out.println(s);
        }
    }
}

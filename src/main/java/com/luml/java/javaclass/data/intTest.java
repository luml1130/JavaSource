package com.luml.java.javaclass.data;

import org.junit.Test;

import java.util.Random;

/**
 * @author luml
 * @description
 * @date 2021/1/13 10:30 上午
 */
public class intTest {

    public static void main(String[] args) {
        int value = Integer.MAX_VALUE;
        System.out.println(value);
        System.out.println(isOdd(8));
    }

    /**
     * int 类整数的最大值是 2 的 31 次方 - 1 = 2147483648 - 1 = 2147483647
     * -2的31次方 ~ 2的31次方-1  -2147483648,到2147483647共10位
     * 可以用 Integer.MAX_VALUE 表示它，即 int value = Integer.MAX_VALUE;
     * Integer.MAX_VALUE + 1 = Integer.MIN_VALUE = -2147483648
     */
    @Test
    public void  testLength() {
        int value = Integer.MAX_VALUE;
        System.out.println(value);//2147483647
        int va = Integer.MIN_VALUE;//-2147483648
        System.out.println(va);
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

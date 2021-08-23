package com.luml.java.javaclass.Integer;

/**
 * @author luml
 * @description
 * @date 2021/1/25 5:32 下午
 */
public class IntTest {

    public static void main(String[] args) {

        //System.out.println(isOdd(3));
    }

    /**
     * 校验数字是否是奇数
     * @param a
     * @return
     */
    public static boolean isOdd(int a) {
        if ((a & 1) != 0) {
            return true;
        }
        return false;
        /**
         * if(row%2==1) 就是奇数
         *  bug点：如果row是负奇数，那么row%2==-1
         *  解决方法：考虑使用x&1==1或者x%2!=0
         */
    }
}

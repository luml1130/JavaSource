package com.luml.gof._2_behavior._9_Strategy;

/**
 * @author luml
 * @description
 * @date 2020/12/8
 */
public class UnionPayStrategy implements PayStrategy{
    @Override
    public void pay(Float cost) {
        System.out.println("通过银联支付了" + cost + "元.");
    }
}

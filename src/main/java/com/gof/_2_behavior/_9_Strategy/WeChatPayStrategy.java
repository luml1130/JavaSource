package com.gof._2_behavior._9_Strategy;

/**
 * @author luml
 * @description
 * @date 2020/12/8
 */
public class WeChatPayStrategy implements com.luml.gof.behavior.Strategy.PayStrategy {
    @Override
    public void pay(Float cost) {
        System.out.println("通过微信支付了" + cost + "元.");
    }
}

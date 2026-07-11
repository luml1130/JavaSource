package com.gof._2_behavior._9_Strategy;

/**
 * @author luml
 */
public interface PayStrategy {

    /**
     * 支付
     *
     * @param cost 支付金额
     */
    void pay(Float cost);
}

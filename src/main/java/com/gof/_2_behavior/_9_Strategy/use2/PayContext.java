package com.gof._2_behavior._9_Strategy.use2;

import com.gof._2_behavior._9_Strategy.PayStrategy;

import java.util.Objects;

/**
 * @author luml
 * @description
 * @date 2020/12/8
 */
public class PayContext {

    private PayStrategy payStrategy;

    /**
     * 选择支付策略
     */
    public PayContext setPayStrategy(PayStrategy payStrategy) {
        this.payStrategy = payStrategy;
        return this;
    }

    /**
     * 支付
     */
    public void pay(PayRequest request ) {
        if (Objects.isNull(payStrategy)) {
            throw new IllegalStateException("未选择支付策略");
        } else {
            payStrategy.pay(request.getTotalMoney());
        }
    }
}

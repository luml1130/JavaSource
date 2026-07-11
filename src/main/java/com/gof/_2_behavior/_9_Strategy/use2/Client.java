package com.gof._2_behavior._9_Strategy.use2;

import com.gof._2_behavior._9_Strategy.AliPayStrategy;
import com.gof._2_behavior._9_Strategy.PayStrategy;
import com.gof._2_behavior._9_Strategy.UnionPayStrategy;
import com.gof._2_behavior._9_Strategy.common.PayType;

import java.util.Objects;

/**
 * @author luml
 * @description
 * @date 2020/12/8
 */
public class Client {
    public static void main(String[] args) {
        // 获取请求中的支付渠道标识
        Integer code = 101;
        PayStrategy payStrategy = null;
        PayRequest request = null;
        if (PayType.ALI.getCode().equals(code)) {
            //组装为支付宝支付策略
            payStrategy = new AliPayStrategy();
            // 构造支付宝请求参数
            request = new AliPayRequest();
        }
        if (PayType.WECHAT.getCode().equals(code)) {
            //组装为微信支付策略
            payStrategy = new AliPayStrategy();
            // 构造微信支付请求参数
           // request = new WechatPayRequest();
        }

        if (PayType.UNION.getCode().equals(code)) {
            //组装为银联支付策略
            payStrategy = new UnionPayStrategy();
            // 构造银联支付请求参数
            //request = new UnionPayRequest();
        }

        if (Objects.nonNull(payStrategy)) {
            PayContext payContext = new PayContext();
            payContext.setPayStrategy(payStrategy);
            payContext.pay(request);
        }
    }
}

package com.gof._2_behavior._9_Strategy.use;

import com.gof._2_behavior._9_Strategy.PayStrategy;
import com.gof._2_behavior._9_Strategy.common.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author luml
 * @description
 * @date 2020/12/8
 */
public class StrategyShoppingCart {
    /**
     * 商品列表
     */
    private List<Goods> goodsList;

    /**
     * 支付策略
     */
    private PayStrategy payStrategy;

    public StrategyShoppingCart() {
        goodsList = new ArrayList<>();
    }

    /**
     * 添加商品
     */
    public StrategyShoppingCart addGoods(Goods goods) {
        goodsList.add(goods);
        return this;
    }

    /**
     * 计算总价
     */
    public float totalCost() {
        return goodsList.stream().map(Goods::getPrice).reduce(Float::sum).orElse(0f);
    }

    /**
     * 选择支付策略
     */
    public StrategyShoppingCart setPayStrategy(PayStrategy payStrategy) {
        this.payStrategy = payStrategy;
        return this;
    }

    /**
     * 支付
     */
    public void pay() {
        if (Objects.isNull(payStrategy)) {
            throw new IllegalStateException("未选择支付策略");
        } else {
            payStrategy.pay(totalCost());
        }
    }
}

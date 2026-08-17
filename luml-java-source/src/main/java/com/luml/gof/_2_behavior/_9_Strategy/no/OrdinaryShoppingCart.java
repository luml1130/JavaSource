package com.luml.gof._2_behavior._9_Strategy.no;

import com.luml.gof._2_behavior._9_Strategy.common.Goods;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2020/12/8
 */
public class OrdinaryShoppingCart {
    /**
     * 商品列表
     */
    private List<Goods> goodsList;

    public OrdinaryShoppingCart() {
        goodsList = new ArrayList<>();
    }

    /**
     * 添加商品
     */
    public OrdinaryShoppingCart addGoods(Goods goods) {
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
     * 通过支付宝支付
     */
    public void payWithAlipay() {
        System.out.println("通过支付宝支付了" + totalCost() + "元.");
    }

    /**
     * 通过微信支付
     */
    public void payWithWeChat() {
        System.out.println("通过微信支付了" + totalCost() + "元.");
    }
}

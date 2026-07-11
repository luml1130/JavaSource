package com.gof._2_behavior._9_Strategy.use;

import com.luml.gof.behavior.Strategy.AliPayStrategy;
import com.luml.gof.behavior.Strategy.WeChatPayStrategy;
import com.luml.gof.behavior.Strategy.common.Goods;

/**
 * @author luml
 * @description
 * @date 2020/12/8
 */
public class ShoppingCartDemo {
    public static void main(String[] args) {
        //购物车(策略模式版本)
        //王五的购物行为
        new StrategyShoppingCart()
                .addGoods(new Goods("一箱牛奶", 34.55f))
                .addGoods(new Goods("一瓶白酒", 250.50f))
                .setPayStrategy(new AliPayStrategy()).pay();

        //赵六的购物行为
        new StrategyShoppingCart()
                .addGoods(new Goods("一箱牛奶", 34.55f))
                .addGoods(new Goods("一瓶啤酒", 3.50f))
                .setPayStrategy(new WeChatPayStrategy()).pay();
    }
}

package com.gof._2_behavior._9_Strategy.no;

import com.luml.gof.behavior.Strategy.common.Goods;

/**
 * @author luml
 * @description
 * 扩展性差，每增减一种支付方式，都需要修改OrdinaryShoppingCart，违背了设计模式的开放-封闭原则（OCP，对扩展开放，对修改关闭）。
 * 可复用低，每种支付方式都采用不同的方法命名
 * @date 2020/12/8
 */
public class ShoppingCartDemo {
    public static void main(String[] args) {
        //购物车(普通版本)
        //张三的购物行为
        new OrdinaryShoppingCart()
                .addGoods(new Goods("一箱牛奶", 34.55f))
                .addGoods(new Goods("一瓶白酒", 250.50f))
                .payWithAlipay();

        //李四的购物行为
        new OrdinaryShoppingCart()
                .addGoods(new Goods("一箱牛奶", 34.55f))
                .addGoods(new Goods("一瓶啤酒", 3.50f))
                .payWithWeChat();
    }
}

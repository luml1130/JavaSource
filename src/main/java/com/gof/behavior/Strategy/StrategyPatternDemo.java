package com.gof.behavior.Strategy;

/**
 * 策略模式示例：模拟不同会员等级的折扣计算
 * 该代码展示了策略模式的核心结构：
 *      通过DiscountStrategy接口定义算法族，NormalMemberStrategy等具体类封装不同折扣算法，
 *      ShoppingCart作为上下文环境持有策略引用并执行计算。
 *          这种设计消除了复杂的if-else判断，使得新增会员等级（新策略）时无需修改现有代码，符合开闭原则，
 *          实现了算法的动态切换和解耦。
 */

// 1. 定义策略接口
interface DiscountStrategy {
    /**
     * 计算折扣后的价格
     * @param originalPrice 原价
     * @return 折后价
     */
    double calculate(double originalPrice);
    
    /**
     * 获取策略名称
     * @return 策略名称
     */
    String getStrategyName();
}

// 2. 具体策略实现类：普通会员（无折扣）
class NormalMemberStrategy implements DiscountStrategy {
    @Override
    public double calculate(double originalPrice) {
        return originalPrice;
    }

    @Override
    public String getStrategyName() {
        return "普通会员 (无折扣)";
    }
}

// 3. 具体策略实现类：银卡会员（95折）
class SilverMemberStrategy implements DiscountStrategy {
    @Override
    public double calculate(double originalPrice) {
        return originalPrice * 0.95;
    }

    @Override
    public String getStrategyName() {
        return "银卡会员 (95折)";
    }
}

// 4. 具体策略实现类：金卡会员（85折）
class GoldMemberStrategy implements DiscountStrategy {
    @Override
    public double calculate(double originalPrice) {
        return originalPrice * 0.85;
    }

    @Override
    public String getStrategyName() {
        return "金卡会员 (85折)";
    }
}

// 5. 上下文类 (Context)：持有策略引用，负责执行策略
class ShoppingCart {
    private DiscountStrategy strategy;

    /**
     * 构造函数，注入初始策略
     * @param strategy 折扣策略
     */
    public ShoppingCart(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 动态切换策略
     * @param strategy 新的折扣策略
     */
    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 执行结账计算
     * @param amount 商品总金额
     * @return 最终支付金额
     */
    public double checkout(double amount) {
        if (strategy == null) {
            throw new IllegalStateException("未设置折扣策略");
        }
        System.out.println("当前使用策略: " + strategy.getStrategyName());
        return strategy.calculate(amount);
    }
}

// 6. 客户端测试类
public class StrategyPatternDemo {
    public static void main(String[] args) {
        double goodsPrice = 1000.0;

        System.out.println("--- 策略模式演示 ---");
        System.out.println("商品原价: " + goodsPrice + " 元\n");

        // 场景1: 普通会员
        ShoppingCart cart = new ShoppingCart(new NormalMemberStrategy());

        double finalPrice = cart.checkout(goodsPrice);
        System.out.println("应付金额: " + finalPrice + " 元\n");

        // 场景2: 动态切换为银卡会员
        cart.setStrategy(new SilverMemberStrategy());
        finalPrice = cart.checkout(goodsPrice);
        System.out.println("应付金额: " + finalPrice + " 元\n");

        // 场景3: 动态切换为金卡会员
        cart.setStrategy(new GoldMemberStrategy());
        finalPrice = cart.checkout(goodsPrice);
        System.out.println("应付金额: " + finalPrice + " 元");
    }
}

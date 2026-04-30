package com.gof.behavior.TemplateMethod;
/**
 * 模板方法模式与策略模式结合示例：订单处理系统
 * 
 * 1. 策略接口 (Strategy): 定义可变的具体算法（如支付、验证）
 * 2. 具体策略 (Concrete Strategy): 实现具体的算法逻辑
 * 3. 抽象模板类 (Abstract Template): 定义算法骨架，持有策略引用
 * 4. 上下文/客户端: 组装策略并执行模板方法
 *

 这段代码展示了模板方法模式与策略模式的优雅结合：
     ‌结构解耦‌：OrderProcessor 作为抽象模板类，定义了订单处理的固定流程（验证->前置->支付->后置->通知），体现了模板方法模式中“骨架不变”的特点。
     ‌算法互换‌：PaymentStrategy 接口及其实现类（支付宝、微信、信用卡）封装了易变的支付算法。通过构造函数注入策略，实现了策略模式中“算法可替换”的特点。
     ‌扩展性‌：
         ‌新增支付方式‌：只需实现 PaymentStrategy 接口，无需修改模板类代码，符合开闭原则。
         ‌新增订单类型‌：只需继承 OrderProcessor 并重写特定的钩子方法（如 preProcess 或 postProcess），即可定制不同业务场景（如团购、秒杀）的处理细节。
     ‌职责清晰‌：模板类负责流程控制，策略类负责具体业务逻辑，两者各司其职，降低了系统的复杂度。


 */

import java.util.HashMap;
import java.util.Map;

// 1. 定义上下文数据对象，用于在策略间传递数据
class OrderContext {
    private final Map<String, Object> data = new HashMap<>();

    public OrderContext put(String key, Object value) {
        data.put(key, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) data.get(key);
    }
}

// 2. 定义策略接口：封装变化的部分（支付逻辑、验证逻辑等）
interface PaymentStrategy {
    String getTypeName();
    boolean pay(OrderContext context);
}

// 3. 具体策略实现：支付宝支付
class AlipayStrategy implements PaymentStrategy {
    @Override
    public String getTypeName() {
        return "支付宝";
    }

    @Override
    public boolean pay(OrderContext context) {
        System.out.println(">> [策略] 正在调用支付宝接口进行支付...");
        // 模拟支付成功
        return true;
    }
}

// 4. 具体策略实现：微信支付
class WechatPayStrategy implements PaymentStrategy {
    @Override
    public String getTypeName() {
        return "微信支付";
    }

    @Override
    public boolean pay(OrderContext context) {
        System.out.println(">> [策略] 正在调用微信支付接口进行支付...");
        // 模拟支付成功
        return true;
    }
}

// 5. 具体策略实现：信用卡支付（模拟失败场景）
class CreditCardStrategy implements PaymentStrategy {
    @Override
    public String getTypeName() {
        return "信用卡";
    }

    @Override
    public boolean pay(OrderContext context) {
        System.out.println(">> [策略] 正在验证信用卡信息...");
        System.out.println(">> [策略] 信用卡余额不足，支付失败！");
        return false;
    }
}

// 6. 抽象模板类：定义订单处理的固定骨架
abstract class OrderProcessor {
    
    // 持有策略引用，通过构造函数或setter注入
    protected PaymentStrategy paymentStrategy;

    public OrderProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    /**
     * 模板方法：定义算法骨架，使用 final 防止子类修改流程
     * 流程：验证 -> 前置处理 -> 支付(策略) -> 后置处理 -> 通知
     */
    public final void processOrder(OrderContext context) {
        System.out.println("=== 开始处理订单 ===");
        
        // 步骤1：验证订单（钩子方法或固定逻辑）
        if (!validateOrder(context)) {
            System.out.println("订单验证失败，终止处理。");
            return;
        }

        // 步骤2：前置处理（如锁定库存）
        preProcess(context);

        // 步骤3：执行支付（使用策略模式，多变的部分）
        System.out.println(">> 使用 [" + paymentStrategy.getTypeName() + "] 进行支付");
        boolean isPaid = paymentStrategy.pay(context);

        if (isPaid) {
            // 步骤4：支付成功后的处理
            postProcess(context);
            sendNotification(context, "支付成功");
        } else {
            // 步骤5：支付失败的处理
            handlePaymentFailure(context);
        }
        
        System.out.println("=== 订单处理结束 ===\n");
    }

    // 具体步骤由子类实现或提供默认实现
    
    // 验证逻辑：通常固定，但也可设为抽象供子类扩展
    protected boolean validateOrder(OrderContext context) {
        Double amount = context.get("amount");
        return amount != null && amount > 0;
    }

    // 前置处理：默认空实现，子类可选择覆盖
    protected void preProcess(OrderContext context) {
        System.out.println(">> [模板] 锁定商品库存...");
    }

    // 后置处理：默认空实现
    protected void postProcess(OrderContext context) {
        System.out.println(">> [模板] 更新订单状态为[已支付]...");
        System.out.println(">> [模板] 扣减实际库存...");
    }

    // 失败处理
    protected void handlePaymentFailure(OrderContext context) {
        System.out.println(">> [模板] 释放预占库存...");
        System.out.println(">> [模板] 记录支付失败日志...");
    }

    // 通知用户
    protected void sendNotification(OrderContext context, String status) {
        System.out.println(">> [模板] 发送通知给用户: 订单状态 - " + status);
    }
}

// 7. 具体模板实现：普通订单处理器（继承自抽象模板，通常不需要额外修改，除非有特殊步骤）
class StandardOrderProcessor extends OrderProcessor {
    public StandardOrderProcessor(PaymentStrategy paymentStrategy) {
        super(paymentStrategy);
    }
    
    // 可以重写特定步骤，例如普通订单可能需要特殊的发票处理
    @Override
    protected void postProcess(OrderContext context) {
        super.postProcess(context);
        System.out.println(">> [子类扩展] 生成普通电子发票...");
    }
}

// 8. 具体模板实现：团购订单处理器（可能有不同的前置/后置逻辑）
class GroupBuyOrderProcessor extends OrderProcessor {
    public GroupBuyOrderProcessor(PaymentStrategy paymentStrategy) {
        super(paymentStrategy);
    }

    @Override
    protected void preProcess(OrderContext context) {
        System.out.println(">> [子类扩展] 检查团购人数是否达标...");
        super.preProcess(context);
    }

    @Override
    protected void sendNotification(OrderContext context, String status) {
        System.out.println(">> [子类扩展] 发送团购拼单成功通知给所有参与者...");
    }
}

// 9. 客户端测试类
public class TemplateStrategyDemo {
    public static void main(String[] args) {
        // 准备订单数据
        OrderContext order1 = new OrderContext().put("amount", 100.0).put("orderId", "ORD-001");
        OrderContext order2 = new OrderContext().put("amount", 200.0).put("orderId", "ORD-002");
        OrderContext order3 = new OrderContext().put("amount", 50.0).put("orderId", "ORD-003");

        System.out.println("--- 场景 1: 普通订单 + 支付宝支付 ---");
        // 组合：普通订单模板 + 支付宝策略
        OrderProcessor processor1 = new StandardOrderProcessor(new AlipayStrategy());
        processor1.processOrder(order1);

        System.out.println("--- 场景 2: 团购订单 + 微信支付 ---");
        // 组合：团购订单模板 + 微信策略
        OrderProcessor processor2 = new GroupBuyOrderProcessor(new WechatPayStrategy());
        processor2.processOrder(order2);

        System.out.println("--- 场景 3: 普通订单 + 信用卡支付(模拟失败) ---");
        // 组合：普通订单模板 + 信用卡策略
        OrderProcessor processor3 = new StandardOrderProcessor(new CreditCardStrategy());
        processor3.processOrder(order3);
    }
}

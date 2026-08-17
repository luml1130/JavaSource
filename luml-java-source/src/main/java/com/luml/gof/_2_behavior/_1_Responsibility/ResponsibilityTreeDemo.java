package com.luml.gof._2_behavior._1_Responsibility;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luml
 * @description 责任书模式：
 *  假设场景：一个电商订单处理系统，需要根据 ‌版本号 (Version)‌ 和 ‌订单类型 (OrderType)‌ 进行不同的处理。
 *      V1 版本：普通订单走流程 A，VIP 订单走流程 B。
 *      V2 版本：所有订单走统一流程 C，但大额订单需额外审计。
 * @date 2026/7/24
 */
public class ResponsibilityTreeDemo {
    public static void main(String[] args) {
        // 构建根节点
        VersionRouter rootRouter = new VersionRouter();

        // 测试用例 1: V1 + NORMAL
        OrderContext req1 = new OrderContext("V1", "NORMAL", 500);
        System.out.println(rootRouter.apply(req1));
        // 输出: V1 普通订单处理: 金额 500.0

        // 测试用例 2: V1 + VIP
        OrderContext req2 = new OrderContext("V1", "VIP", 800);
        System.out.println(rootRouter.apply(req2));
        // 输出: V1 VIP订单处理: 金额 800.0, 赠送积分

        // 测试用例 3: V2 + 大额
        OrderContext req3 = new OrderContext("V2", "NORMAL", 20000);
        System.out.println(rootRouter.apply(req3));
        // 输出: V2 大额订单: 触发人工审计, 金额 20000.0

        // 测试用例 4: 未知版本 (触发默认逻辑)
        OrderContext req4 = new OrderContext("V3", "NORMAL", 100);
        rootRouter.setDefaultHandler(ctx -> "未知版本，拒绝处理");
        System.out.println(rootRouter.apply(req4));
        // 输出: 未知版本，拒绝处理
    }
}

/**
 * 步骤 1: 定义基础接口
 *  1. 策略执行接口 2. 请求上下文对象
 */
// 1. 策略执行接口
@FunctionalInterface
interface StrategyHandler<T, R> {
    StrategyHandler<Object, Object> DEFAULT = t -> null;
    /**
     * 执行策略逻辑
     */
    R apply(T param);
}
//2. 请求上下文对象
class OrderContext {
    private String version;
    private String orderType;
    private double amount;

    public OrderContext(String version, String orderType, double amount) {
        this.version = version;
        this.orderType = orderType;
        this.amount = amount;
    }

    public String getVersion() { return version; }
    public String getOrderType() { return orderType; }
    public double getAmount() { return amount; }
}

/**
 * 步骤 2: 定义抽象路由器 (核心框架)
 * 这个类负责“根据参数找下一个节点”，是构建树的关键。
 */
abstract class AbstractStrategyRouter<T, R> implements StrategyHandler<T, R> {

    // 策略映射器：根据入参 T 找到对应的子处理器
    @FunctionalInterface
    public interface StrategyMapper<T, R> {
        StrategyHandler<T, R> get(T param);
    }

    private StrategyMapper<T, R> strategyMapper;
    private StrategyHandler<T, R> defaultHandler = (StrategyHandler<T, R>) StrategyHandler.DEFAULT;

    /**
     * 子类必须实现此方法，注册路由规则
     */
    protected abstract StrategyMapper<T, R> registerStrategyMapper();

    // 初始化时绑定 mapper
    public void init() {
        this.strategyMapper = registerStrategyMapper();
    }

    @Override
    public R apply(T param) {
        // 1. 尝试获取匹配的子策略/子路由器
        StrategyHandler<T, R> handler = strategyMapper.get(param);

        // 2. 如果找到，执行；否则执行默认逻辑
        if (handler != null) {
            return handler.apply(param);
        } else {
            return defaultHandler.apply(param);
        }
    }

    // 设置默认处理器
    public void setDefaultHandler(StrategyHandler<T, R> defaultHandler) {
        this.defaultHandler = defaultHandler;
    }
}

/**
 * 步骤 3: 构建责任树 (具体实现)
 */
//第一层：根据 Version 路由
class VersionRouter extends AbstractStrategyRouter<OrderContext, String> {

    private final StrategyHandler<OrderContext, String> v1Handler;
    private final StrategyHandler<OrderContext, String> v2Handler;

    public VersionRouter() {
        // 注入下一层的处理器（可以是具体策略，也可以是下一级路由器）
        this.v1Handler = new V1OrderRouter(); // V1 还需要根据 OrderType 分，所以是路由器
        this.v2Handler = new V2OrderHandler(); // V2 直接处理，是叶子节点
        this.init();
    }
    @Override
    protected StrategyMapper<OrderContext, String> registerStrategyMapper() {
        Map<String, StrategyHandler<OrderContext, String>> map = new HashMap<>();
        map.put("V1", v1Handler);
        map.put("V2", v2Handler);

        // 返回一个 Lambda，根据 context 的 version 字段路由
        return (ctx) -> map.get(ctx.getVersion());
    }
}

//第二层：V1 版本下，根据 OrderType 路由
class V1OrderRouter extends AbstractStrategyRouter<OrderContext, String> {
    private final StrategyHandler<OrderContext, String> normalHandler;
    private final StrategyHandler<OrderContext, String> vipHandler;

    public V1OrderRouter() {
        this.normalHandler = ctx -> "V1 普通订单处理: 金额 " + ctx.getAmount();
        this.vipHandler = ctx -> "V1 VIP订单处理: 金额 " + ctx.getAmount() + ", 赠送积分";
        this.init();
    }
    @Override
    protected StrategyMapper<OrderContext, String> registerStrategyMapper() {
        Map<String, StrategyHandler<OrderContext, String>> map = new HashMap<>();
        map.put("NORMAL", normalHandler);
        map.put("VIP", vipHandler);

        return (ctx) -> map.get(ctx.getOrderType());
    }
}

//叶子节点：V2 版本的具体处理逻辑
class V2OrderHandler implements StrategyHandler<OrderContext, String> {
    @Override
    public String apply(OrderContext ctx) {
        if (ctx.getAmount() > 10000) {
            return "V2 大额订单: 触发人工审计, 金额 " + ctx.getAmount();
        }
        return "V2 普通订单: 自动通过, 金额 " + ctx.getAmount();
    }
}


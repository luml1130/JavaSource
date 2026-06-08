package com.luml.threadPool.Executor.CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * 以下是一个综合性的示例，展示了从‌创建异步任务‌、‌链式处理结果‌、‌组合多个任务‌到‌异常处理‌的完整流程。
 * 综合示例：模拟电商订单查询场景
 * 假设我们需要执行以下步骤：
 *     异步获取用户 ID。
 *     根据用户 ID 异步获取用户详细信息。
 *     同时异步获取该用户的订单列表。
 *     将用户信息和订单信息组合成最终结果。
 *     如果任何步骤出错，提供默认值或错误处理。
 * @date 2026/5/18
 */
public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1. 模拟异步获取用户ID (supplyAsync: 有返回值的异步任务)
        CompletableFuture<Integer> userIdFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("[线程: " + Thread.currentThread().getName() + "] 正在查询用户ID...");
            sleep(1000); // 模拟耗时
            return 1001;
        });

        // 2. 根据用户ID异步获取用户姓名 (thenApply: 对上一个结果进行转换，同步执行)
        // 注意：如果获取姓名也是耗时IO操作，建议使用 thenCompose 结合另一个 supplyAsync
        CompletableFuture<String> userNameFuture = userIdFuture.thenApply(userId -> {
            System.out.println("[线程: " + Thread.currentThread().getName() + "] 正在根据ID: " + userId + " 查询用户名...");
            return "User-" + userId;
        });

        // 3. 异步获取订单列表 (独立的异步任务)
        CompletableFuture<List<String>> ordersFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("[线程: " + Thread.currentThread().getName() + "] 正在查询订单列表...");
            sleep(1500); // 模拟耗时
            return Arrays.asList("Order-A", "Order-B", "Order-C");
        });

        // 4. 组合两个独立的任务结果 (thenCombine: 当两个Future都完成时，合并结果)
        CompletableFuture<String> finalResultFuture = userNameFuture.thenCombine(ordersFuture, (name, orders) -> {
            System.out.println("[线程: " + Thread.currentThread().getName() + "] 正在组装最终结果...");
            String orderStr = orders.stream().collect(Collectors.joining(", "));
            return "用户: " + name + ", 订单: [" + orderStr + "]";
        });

        // 5. 添加异常处理 (exceptionally: 如果上述任何阶段抛出异常，执行此逻辑)
        CompletableFuture<String> safeResultFuture = finalResultFuture.exceptionally(ex -> {
            System.err.println("发生错误: " + ex.getMessage());
            return "默认用户: Guest, 订单: []";
        });

        // 6. 设置任务完成后的回调 (whenComplete: 无论成功或失败都会执行，不改变结果)
        safeResultFuture.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("✅ 最终结果: " + result);
            } else {
                System.err.println("❌ 处理失败: " + ex.getMessage());
            }
        });

        // 7. 阻塞主线程等待结果（在实际Web应用中通常不需要阻塞，而是直接返回Future或由框架处理）
        // 这里为了演示效果，让主线程等待所有异步任务完成
        System.out.println("主线程继续执行其他工作...");
        String finalOutput = safeResultFuture.get();
        System.out.println("程序结束。获取到的结果: " + finalOutput);
    }

    // 辅助方法：模拟耗时
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

package com.luml.threadOther.threadLocal;

/**
 * @author luml
 * @description
 * @date 2026/7/11
 */
public class MultiThreadLocalExample {
    // 定义第一个 ThreadLocal，用于存储用户 ID (String)
    private static final ThreadLocal<String> userIdContext = new ThreadLocal<>();

    // 定义第二个 ThreadLocal，用于存储事务 ID (Long)
    private static final ThreadLocal<Long> transactionIdContext = new ThreadLocal<>();

    // 定义第三个 ThreadLocal，用于存储请求追踪 ID (String)，并设置默认初始值
    private static final ThreadLocal<String> traceIdContext = ThreadLocal.withInitial(() -> "DEFAULT-TRACE-ID");

    /**
     * 模拟业务处理方法
     */
    public static void processRequest(String userId, Long transactionId, String traceId) {
        try {
            // 1. 设置多个 ThreadLocal 变量的值
            userIdContext.set(userId);
            transactionIdContext.set(transactionId);
            if (traceId != null) {
                traceIdContext.set(traceId);
            }

            // 2. 模拟在不同层级的方法调用中获取数据
            simulateServiceLayer();
            simulateDaoLayer();

        } finally {
            // 3. 【重要】务必在 finally 块中清除 ThreadLocal 变量，防止内存泄漏和数据污染
            // 特别是在线程池环境下，线程会被复用，不清除会导致下一个任务读到旧数据
            userIdContext.remove();
            transactionIdContext.remove();
            traceIdContext.remove();
        }
    }

    /**
     * 模拟服务层逻辑：无需参数传递，直接通过 ThreadLocal 获取上下文
     */
    private static void simulateServiceLayer() {
        String userId = userIdContext.get();
        Long txId = transactionIdContext.get();
        System.out.println("[Service Layer] 处理用户: " + userId + ", 事务ID: " + txId);
    }

    /**
     * 模拟 DAO 层逻辑：同样可以直接获取上下文
     */
    private static void simulateDaoLayer() {
        String traceId = traceIdContext.get();
        System.out.println("[DAO Layer] 记录日志，追踪ID: " + traceId);
    }

    public static void main(String[] args) {
        // 创建两个线程模拟并发请求
        Thread thread1 = new Thread(() -> {
            System.out.println("=== 线程 1 开始 ===");
            processRequest("User_A", 1001L, "TRACE-001");
            System.out.println("=== 线程 1 结束 ===");
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            System.out.println("=== 线程 2 开始 ===");
            processRequest("User_B", 2002L, "TRACE-002");
            System.out.println("=== 线程 2 结束 ===");
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}

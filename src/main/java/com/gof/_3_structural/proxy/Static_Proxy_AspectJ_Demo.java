package com.gof._3_structural.proxy;

/**
 * 代码说明：
 * 1. AspectJ 通过 aspect 关键字定义切面，使用 pointcut 定义连接点，通过 before/after/around 等通知类型织入逻辑。
 * 2. 必须使用 AspectJ 编译器 ajc 进行编译，或在运行时通过 -javaagent 启用加载时织入（LTW），否则切面不会生效。
 * 3. 相比 JDK/CGLIB 动态代理，AspectJ 能代理任意类和方法，无接口限制，且运行时无反射开销，性能更优。
 */
// 3. AspectJ 切面定义 (通常写在 .aj 文件中，此处为示意)
// 注意：实际开发中需使用 ajc 编译器编译，或使用 LTW (Load-Time Weaving)
/*
public aspect TransactionAspect {
    // 定义切点：拦截 UserService 接口的所有方法
    pointcut serviceMethods(): execution(* UserService+.*(..));

    // 前置通知
    before(): serviceMethods() {
        System.out.println("前置操作：开启事务");
    }

    // 后置通知
    after(): serviceMethods() {
        System.out.println("后置操作：提交事务");
    }
    
    // 环绕通知示例
    void around(): serviceMethods() {
        System.out.println("环绕：开始");
        proceed(); // 执行原方法
        System.out.println("环绕：结束");
    }
}
*/

// 1. 定义业务接口
interface UserService2 {
    void addUser(String name);
}

// 2. 真实业务类
class UserServiceImpl2 implements UserService2 {
    @Override
    public void addUser(String name) {
        System.out.println("添加用户: " + name);
    }
}

// 4. 测试类
public class Static_Proxy_AspectJ_Demo {
    public static void main(String[] args) {
       UserService2 service = new UserServiceImpl2();
        // 经过 ajc 编译后，service 对象的方法调用会被织入切面逻辑
        service.addUser("Alice");
    }
}

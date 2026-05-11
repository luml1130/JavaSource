package com.gof.structural.proxy;

// 1. 定义公共接口
interface UserService {
    void addUser(String name);
}

// 2. 真实主题类（被代理类）
class UserServiceImpl implements UserService {
    @Override
    public void addUser(String name) {
        System.out.println("添加用户: " + name);
    }
}

// 3. 代理类
class UserServiceProxy implements UserService {
    private UserService target;

    public UserServiceProxy(UserService target) {
        this.target = target;
    }

    @Override
    public void addUser(String name) {
        // 前置增强
        System.out.println("前置操作：权限检查");
        // 调用真实对象方法
        target.addUser(name);
        // 后置增强
        System.out.println("后置操作：日志记录");
    }
}

/**
 * 静态代理：
 * 接口定义规范：代理类和真实类必须实现相同接口，确保行为一致。
 * 组合持有引用：代理类通过构造函数持有真实对象的引用，而非继承。
 * 功能增强逻辑：在调用真实方法前后插入额外逻辑如日志或权限校验。
 * 编译期绑定：代理关系在编译时已固定，每个目标类需单独编写代理类。
 */
public class StaticProxyDemo {

    // 4. 客户端测试
    public static void main(String[] args) {
        UserServiceImpl realService = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy(realService);
        proxy.addUser("Mike");
    }
}

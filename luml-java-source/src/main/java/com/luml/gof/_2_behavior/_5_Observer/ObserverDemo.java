package com.luml.gof._2_behavior._5_Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description 以下是一个标准的 Java 实现示例，模拟“微信公众号发布消息，用户接收通知”的场景：
 * @date 2026/7/26
 */
// 1. 抽象观察者接口
interface Observer {
    void update(String message);
}
// 4. 具体观察者：微信用户
class WechatUser implements Observer {
    private String name;
    public WechatUser(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println(name + " 收到推送: " + message);
    }
}

// 2. 抽象主题接口
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}
// 3. 具体主题：微信公众号
class WechatOfficialAccount implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String message;
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    // 发布新消息，触发通知
    public void publishMessage(String msg) {
        this.message = msg;
        System.out.println("公众号发布新消息: " + msg);
        notifyObservers();
    }
}


// 测试客户端
public class ObserverDemo {
    public static void main(String[] args) {
        WechatOfficialAccount account = new WechatOfficialAccount();

        WechatUser user1 = new WechatUser("张三");
        WechatUser user2 = new WechatUser("李四");

        // 订阅
        account.attach(user1);
        account.attach(user2);

        // 发布消息，两人都会收到
        account.publishMessage("Java设计模式精讲！");

        // 李四取消订阅
        account.detach(user2);

        // 再次发布，只有张三收到
        account.publishMessage("观察者模式实战教程！");
    }
}

package com.gof.structural.adaptor;

// 目标接口（客户端期望的接口）
interface Target {
    void request();
}

// 被适配者（已存在的、接口不兼容的类）
class Adaptee {
    public void specificRequest() {
        System.out.println("特殊请求!");
    }
}

// 适配器（实现目标接口，并持有被适配者实例）
class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

// 客户端代码
 class Client {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.request();
    }
}

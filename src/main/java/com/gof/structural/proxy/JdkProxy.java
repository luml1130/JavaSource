package com.gof.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author luml
 * @description
 * @date 2020/4/21 22:28
 */
public class JdkProxy {
    public static void main(String[] args) {
        //真实对象
        Subject realSubject =  new RealSubject();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);
        //代理对象
        Subject proxyClass = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{Subject.class}, myInvocationHandler);
        proxyClass.sellBooks();
        proxyClass.speak();
    }
    /**
     * 调用代理类
     * 卖书
     * 调用的是卖书的方法
     * 调用代理类
     * 说话
     * 调用的是说话的方法
     */
}

/**
 * 接口：Subject.java
 */
interface Subject {
     int sellBooks();
     String speak();
}

/**
 * 真实对象：RealSubject.java
 */
class RealSubject implements Subject {
    @Override
    public int sellBooks() {
        System.out.println("卖书");
        return 1 ;
    }
    @Override
    public String speak() {
        System.out.println("说话");
        return "张三";
    }
}

/**
 * 处理器对象：MyInvocationHandler.java; * 因为需要处理真实角色，所以要把真实角色传进来
 */
class MyInvocationHandler implements InvocationHandler {
    Subject realSubject ;
    public MyInvocationHandler(Subject realSubject) {
        this.realSubject = realSubject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用代理类");
        if(method.getName().equals("sellBooks")){
            int invoke = (int)method.invoke(realSubject, args);
            System.out.println("调用的是卖书的方法");
            return invoke ;
        }else {
            String string = (String) method.invoke(realSubject,args) ;
            System.out.println("调用的是说话的方法");
            return  string ;
        }
    }
}
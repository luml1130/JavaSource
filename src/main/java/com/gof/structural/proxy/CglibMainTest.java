package com.gof.structural.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author luml
 * @description
 * @date 2020/4/21 22:20
 */
public class CglibMainTest {
    public static void main(String[] args) {
        // 生成 Cglib 代理类
        com.luml.gof.structural.proxy.Engineer engineerProxy = (com.luml.gof.structural.proxy.Engineer) com.luml.gof.structural.proxy.CglibProxy.getProxy(new com.luml.gof.structural.proxy.Engineer());
        // 调用相关方法
        engineerProxy.eat();
    }
    /**
     * ###   before invocation
     * 工程师正在吃饭
     * ###   end invocation
     */
}

/**
 * 需要代理的类
 */
class Engineer {
    // 可以被代理
    public void eat() {
        System.out.println("工程师正在吃饭");
    }
    // final 方法不会被生成的字类覆盖
    public final void work() {
        System.out.println("工程师正在工作");
    }
    // private 方法不会被生成的字类覆盖
    private void play() {
        System.out.println("this engineer is playing game");
    }
}

/**
 * CGLIB 代理类:
 */
class CglibProxy implements MethodInterceptor {
    private Object target;
    public CglibProxy(Object target) {
        this.target = target;
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {
        System.out.println("###   before invocation");
        Object result = method.invoke(target, objects);
        System.out.println("###   end invocation");
        return result;
    }
    public static Object getProxy(Object target) {
        Enhancer enhancer = new Enhancer();
        // 设置需要代理的对象
        enhancer.setSuperclass(target.getClass());
        // 设置代理人
        enhancer.setCallback(new com.luml.gof.structural.proxy.CglibProxy(target));
        return enhancer.create();
    }
}

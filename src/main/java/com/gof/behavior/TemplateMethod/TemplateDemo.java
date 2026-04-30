package com.gof.behavior.TemplateMethod;

/**
 * @author luml
 * @description
 *  1、先定义一个接口，主要是定义了模板方法
 *  2、抽象类实现了接口，主要是实现了模板方法的逻辑，
 *   	模板方法中调用了自己的逻辑方法，还有最重要的钩子方法和抽象方法
 *  3、两个子类，One只实现了抽象方法，Two实现了抽象方法并覆盖了钩子方法
 * @date 2026/4/30
 */
//1、模版方法接口
interface TemplateInterface {
    void execute();
}
//2、抽象模板类：定义固定骨架
abstract class TemplateAbstractClass
        implements TemplateInterface {

    @Override
    public void execute() {
        preDoSomething();
        abstractMethod();
        hookMethod();
        afterDoSomething();
    }
    private void preDoSomething(){
        System.out.println("before do some thing in abstract class");
    }
    private void afterDoSomething(){
        System.out.println("after do some thing in abstract class");
    }

    /**抽象方法*/
    public abstract void abstractMethod();

    /**钩子方法*/
    public void hookMethod(){
    }

}

class SubClassOne extends TemplateAbstractClass {

    /**抽象方法*/
    @Override
    public void abstractMethod() {
        System.out.println("do another thing by subClassOne");
    }

}

class SubClassTwo extends TemplateAbstractClass {

    //抽象方法
    @Override
    public void abstractMethod() {
        System.out.println("do another thing by subClassTwo");
    }

    /**钩子方法*/
    @Override
    public void hookMethod(){
        System.out.println("hook method in subClassTwo");
    }

}



public class TemplateDemo {

    public static void main(String[] args) {
        System.out.println("--- 场景 1: SubClassOne ---");
        SubClassOne processor1 = new SubClassOne();
        processor1.execute();
        /**
         * --- 场景 1: SubClassOne ---
         * before do some thing in abstract class
         * do another thing by subClassOne
         * after do some thing in abstract class
         */

        System.out.println("--- 场景 2: SubClassTwo ---");
        SubClassTwo processor2 = new SubClassTwo();
        processor2.execute();
        /**
         * --- 场景 2: SubClassTwo ---
         * before do some thing in abstract class
         * do another thing by subClassTwo
         * hook method in subClassTwo //钩子方法 特定方法
         * after do some thing in abstract class
         */
    }
}

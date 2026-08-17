package com.luml.gof._1_create.single;

/**
 * @author luml
 * @description:
 * 懒汉式单例:并发会有问题
 * @date 2020/11/26
 */
public class C_1_Singleton {
    //注意这是private 只供内部调用
    private static C_1_Singleton single = null;
    private C_1_Singleton(){}

    public C_1_Singleton getInstance(){
        if (single == null) {
            single = new C_1_Singleton();
        }
        return single;
    }

}


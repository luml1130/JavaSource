package com.gof._1_create.single;

/**
 * @author luml
 * @description:
 * 在getInstance方法上加同步
 * @date 2020/11/26
 */
public class C_1_Singleton_01 {
    //注意这是private 只供内部调用
    private static C_1_Singleton_01 single = null;
    private C_1_Singleton_01(){}

    public synchronized C_1_Singleton_01 getInstance(){
        if (single == null) {
            single = new C_1_Singleton_01();
        }
        return single;
    }
}

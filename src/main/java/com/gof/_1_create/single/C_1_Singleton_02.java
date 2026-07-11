package com.gof._1_create.single;

/**
 * @author luml
 * @description:  DCL
 *  double check lock:
 * @date 2020/11/26
 */
public class C_1_Singleton_02 {
    //注意这是private 只供内部调用
    private static volatile  /**/ C_1_Singleton_02 single = null;
    private C_1_Singleton_02(){}

    public C_1_Singleton_02 getInstance(){
        if (single == null) {
            synchronized(C_1_Singleton_02.class){
                single = new C_1_Singleton_02();
            }
        }
        return single;
    }
}

package com.luml.java.base;

/**
 * @author luml
 * @description
 * @date 2021/8/22 下午6:18
 */
public class OverLoadTest {

    //一个普通得方法，不带参数，无返回值
    public void add(){
        //method body
    }

    //重载上面的方法，并且带了一个整形参数，无返回值
    public void add(int a){
        //method body
    }

    //重载上面的方法，并且带了两个整型参数，返回值为int型
    public int add(int a,int b){
        //method body
        return 0;
    }
}

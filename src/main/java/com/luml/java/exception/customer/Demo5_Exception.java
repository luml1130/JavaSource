package com.luml.java.exception.customer;

/**
 * @author luml
 * @description
 * @date 2026/7/20
 */
public class Demo5_Exception {

    public static void main(String[] args) throws Exception {
        Div2 d = new Div2();
        int x = d.div(10, -5);
        System.out.println(x);
    }
}

class Div2 {
    public int div(int a,int b) throws Exception {
        if(b <= 0) {
            NegativeException n = new NegativeException("除数不能为负数和零");
            throw n;
            //Exception e = new Exception("除数不能为负数和零");
            //throw e;
        }
        return a / b;
    }
}
class NegativeException extends Exception {
    public NegativeException(String message) {
        super(message);
    }
}

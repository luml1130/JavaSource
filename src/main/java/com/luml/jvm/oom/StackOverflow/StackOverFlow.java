package com.luml.jvm.oom.StackOverflow;

/**
 * @author luml
 * @description:栈层级不足
 * @VM args:-Xss128k
 * @date 2021/4/5 11:43 下午
 */
public class StackOverFlow {
    private int i;

    public void plus() {
        i++;
        plus();
    }

    public static void main(String[] args) {
        StackOverFlow stackOverFlow = new StackOverFlow();
        try {
            stackOverFlow.plus();
        } catch (Error e) { //不能破获exceprion哦 因为 是error  可以捕获 throwable
            System.out.println("Error:stack length:" + stackOverFlow.i);
            e.printStackTrace();
        }
    }
    /**
     * Error:stack length:997
     * java.lang.StackOverflowError
     *     at com.luml.jvm.oom.StackOverflow.StackOverFlow.plus(StackOverFlow.java:14)
     */
}

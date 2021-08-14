package com.luml.jvm.oom.StackOverflow;

/**
 * @author luml
 * @description:递归Constructer
 * @VM args:-Xss128k
 * @date 2021/4/5 11:45 下午
 */
public class StackOverFlow2 {

    public class OneObject {
        OneObject oneObject = new OneObject();
    }

    public static void main(String[] args) {
        StackOverFlow2 stackOverFlow2 = new StackOverFlow2();
        try {
            OneObject oneObject = stackOverFlow2.new OneObject();
            /**
             * 在这里  constructer 中是调用 init , 而 static 是调用 cinit ,
             * 固我们如果将自己的对象放入到 static 中是不会造成递归的,
             * 而如果将自己本身放到 constructer 中他就会不断的调用 init ,递归并不是马上返回，
             *              而是一层一层的保存在Stack里边，满足结束条件后才一层一层的返回。
             * 注意：是递归的错误，才出现Stack满的情况，
             *              而无限循环一般不会占用更多的内存或者具体的Stack，只是占cpu而已，所以不会抛此错误。
             */
            //反编译
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Exception in thread "main" java.lang.StackOverflowError
     * 	at com.luml.jvm.oom.StackOverflow.StackOverFlow2$OneObject.<init>(StackOverFlow2.java:12)
     */

}

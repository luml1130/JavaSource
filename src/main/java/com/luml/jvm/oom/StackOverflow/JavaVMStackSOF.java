package com.luml.jvm.oom.StackOverflow;

/**
 * @author luml
 * @description：虚拟机栈OOM
 * HotSpot虚拟机中并不区分虚拟机栈和本地方法栈，统一是虚拟机栈。
 * 栈容量只由-Xss参数设定。
 * 在Java虚拟机规范中描述了2种异常：
 * （1）如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError异常；
 * （2）如果虚拟机在扩展栈时无法申请到足够的内存空间，则抛出OOM。
 * 测试时：VM -Xss108k
 * @date 2020/8/31
 */
public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }
    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable t) {
            System.out.println(oom.stackLength);
            throw t;
        }
    }
}

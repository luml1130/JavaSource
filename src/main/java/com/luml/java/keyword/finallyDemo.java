package com.luml.source.keyword;

import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2026/7/20
 */
public class finallyDemo {

    public static void main(String[] args) {
        int result = testReturn2();
        System.out.println(result);
        /**
         * finally executed
         * 1
         */
    }

    /**
     * 无论 try 块中是否发生异常，也无论 catch 块是否捕获并处理了异常，finally 块中的代码‌几乎总会执行‌。
     * 如果在 try 或 catch 块中调用了 System.exit(0) 强制终止 JVM，或者线程被杀死、断电等极端情况，finally 块可能不会执行。
     *  I'm nomal I'm finally.
     */
    @Test
    public void test1(){
        try {
            System.out.println("I'm nomal");
        } catch (Exception e) {
            System.out.println("I'm exception");
        }finally{
            System.out.println("I'm finally.");
        }
    }

    /**
     * 输出结构：I'm nomal、I'm finally.
     */
    @Test
    public void testReturn(){
        try {
            System.out.println("I'm nomal");
            return;
        } catch (Exception e) {
            System.out.println("I'm exception");
            return;
        }finally{
            System.out.println("I'm finally.");
        }
        //System.out.println("Out of try."); 这个会编译错误
    }


    public static int testReturn2() {
        try {
            return 1; // 1. 计算返回值 并 暂存
        } finally {
            System.out.println("finally executed"); // 2. 执行 finally 代码
            // 如果这里没有 return，方法最后返回 1
            // 如果这里有 return 2; 方法最后返回 2
        }
    }

    /**
     * 输出：I'm nomal
     */
    @Test
    public void testExit(){
        try {
            System.out.println("I'm nomal");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("I'm exception");
           // return;
            System.exit(0);
        }finally{
            System.out.println("I'm finally.");
        }

    }
}

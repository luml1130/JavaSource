package com.luml.java.reflect.study;

import com.luml.java.reflect.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author luml
 * @description
 * @date 2021/11/17
 */
public class test {

    public static void main(String[] args) throws Exception {
        Class clazz=Class.forName("com.luml.java.reflect.Person");
        Person p = (Person) clazz.newInstance();

        /**
         * 获取共有方法
         */
        Method me = clazz.getMethod("eat", String.class);
        String s=(String) me.invoke(p, "苹果");
        System.out.println(s);
        //我爱吃苹果

        /**
         * 获取私有方法
         */
        Method priMe = clazz.getDeclaredMethod("privateEat",String.class);
        priMe.setAccessible(true);
        String s2 = (String) priMe.invoke(p, "橘子");
        System.out.println(s2);
        //我就是爱吃橘子

        /**
         * 静态方法
         */
        Method staticMe = clazz.getDeclaredMethod("say");
        // 设置访问权限
        staticMe.setAccessible(true);
        // 3.让方法执行.
        staticMe.invoke(null);

        /**
         * main方法
         * 因为// JDK本身. 1.5是用可变参数做的
         * // JDK1.5 m.invoke(Object obj,Object... args);
         * // JDK1.4 m.invoke(Object obj,Object[] args);  将数组 拆开  aaa bbb
         * 这时就会向下兼容 先使用1.4的 我们的数组就变成aaa bbb 就会调用带两个参数的方法 有吗 没有 所以就报了一个错误的参数个数错误
         *
         * 所以就不能让他拆开；有两种方法
         * me.invoke(null,new Object[]{new String[]{"aaa","bbb"}});
         * 或者me.invoke(null,(Object)new String[]{"aaa","bbb"});
         * 输出结果main……
         */
        // 2.获得代码操作的方法的对象Method。
        Method mainMe = clazz.getDeclaredMethod("main", String[].class);
        mainMe.setAccessible(true);
        // 3.执行.
        //m.invoke(null,new String[]{"aaa","bbb"});错误
        mainMe.invoke(null,new Object[]{new String[]{"aaa","bbb"}});
        mainMe.invoke(null,(Object)new String[]{"aaa","bbb"});
    }

    @Test
    public void getField(){
        try {
            Class clazz= Class.forName("com.luml.java.reflect.Person");
            Person p=(Person) clazz.newInstance();
            /**
             * 公用的属性
             */
            Field f = clazz.getField("price");
            f.set(p, 56);
            System.out.println(f.get(p));

            /**
             * 私有的属性
             */
            Field f2 = clazz.getDeclaredField("name");
            f2.setAccessible(true);
            f2.set(p, "许文强");
            System.out.println(f2.get(p));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

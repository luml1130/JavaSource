package com.luml.source.java.jdkNewFeature.jdk8.functional.BaseFour.consumer;

import com.luml.source.domain.User3;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BiConsumerTest {
    public static void main(String[] args) {
        BiConsumer<String,Integer> printPair = (name,age) ->{
            System.out.println("姓名: " + name + ", 年龄: " + age);
        };
        printPair.accept("小小水", 18);

        /**
         * 常用场景：遍历 Map
         * Map.forEach 参数就是 BiConsumer<K,V>，最常用！
         */
        Map<String,Integer> map = new HashMap<>();
        map.put("A",10);
        map.put("B",20);
        map.forEach( (k, v) -> {
            System.out.println(k + " -> " + v);
        });
    }

    /**
     * andThen 链式调用
     */
    @Test
    public void test1(){
        BiConsumer<String,Integer> first = (s,i) -> System.out.println(s + ":" + i);
        BiConsumer<String,Integer> second = (s,i) -> System.out.println("值*2=" + i*2);
        // 链式组合
        BiConsumer<String,Integer> combine = first.andThen(second);

        combine.accept("test",5);
    }

    /**
     * 方法引用写法
     * // 假设有工具类
     */
    @Test
    public void test2(){
        BiConsumer<String,Integer> bi = Util::show;
        bi.accept("hello",100);
    }

    /**
     * 实际业务小例子：修改两个对象属性
     */
    @Test
    public void test3(){
       User3 u1 = new User3();
       User3 u2 = new User3();
       BiConsumer<User3,User3> setName = (a,b)->{
           u1.setName("小小水");
           u2.setName("大大水");
       };
       setName.accept(u1,u2);
       System.out.println(u1);
       System.out.println(u2);
    }

}


class Util{
    static void show(String s,Integer i){
        System.out.println(s + "|" + i);
    }
}

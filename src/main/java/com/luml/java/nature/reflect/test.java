package com.luml.java.nature.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author luml
 * @description
 * @date 2020/9/3
 */
public class test {


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
       // Constructor  String .class.getConstructor(StringBuffer.class);
       Constructor constructor =  String.class.getConstructor(StringBuffer.class);
       String strt2  = (String) constructor.newInstance(new StringBuffer("abc"));
        System.out.println(strt2);
    }

    @Test
    public void getFiled(){
        Class<Person> personClass = Person.class;
        Field[] fields = personClass.getDeclaredFields(); // 获取当前类的所有字段
        for (Field field : fields) {
            //System.out.println("Field name: " + field.getName());
            //System.out.println("Field type: " + field.getType().getName());
            //System.out.println();

        }
    }
}

package com.luml.java.nature.reflect;

import com.luml.java.nature.reflect.domain.Person;
import com.luml.java.nature.reflect.domain.ReflectFieldExample;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @Test
    public void getFieldValue(){
        try {
            List<String> handlerList = Arrays.asList("age");
            ReflectFieldExample person = new ReflectFieldExample("张三", 25, "zhangsan@example.com");
            Map<String, Object> fieldsValue = reflectUtils.getFieldsValue(person);

            /*for (Map.Entry<String, Object> entry : fieldsValue.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());

            }*/

            /*for (String key : handlerList) {
                if (fieldsValue.containsKey(key)) {
                    //result1.put(key, map.get(key));
                    System.out.println(fieldsValue.get(key));
                }
            }*/
            Boolean isMath = handlerList.stream()
                    .anyMatch( x-> (Integer)fieldsValue.get(x) > 26);

            System.out.println(isMath);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}

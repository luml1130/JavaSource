package com.luml.source.nature.reflect.demo;

import java.lang.reflect.Field;

/**
 * @author luml
 * @description
 * 实际应用场景示例：通用属性复制工具
 * 反射常用于编写通用工具类，例如将对象 A 的属性复制到对象 B
 *      （类似 Spring 的 BeanUtils.copyProperties 简化版）。
 * @date 2026/7/6
 */
public class BeanCopyUtil {
    /**
     * 将 source 对象的字段值复制到 target 对象
     * 仅复制字段名相同且类型兼容的字段
     */
    public static void copyProperties(Object source, Object target) throws Exception {
        if (source == null || target == null) {
            return;
        }

        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();

        // 获取源对象的所有声明字段
        Field[] fields = sourceClass.getDeclaredFields();

        for (Field sourceField : fields) {
            sourceField.setAccessible(true);

            try {
                // 在目标类中查找同名字段
                Field targetField = targetClass.getDeclaredField(sourceField.getName());
                targetField.setAccessible(true);

                // 获取源字段的值
                Object value = sourceField.get(source);

                // 设置到目标字段
                if (value != null) {
                    targetField.set(target, value);
                }
            } catch (NoSuchFieldException e) {
                // 目标类没有该字段，跳过
                continue;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        User user1 = new User("SourceUser", 100);
        User user2 = new User(); // 初始为 null, 0

        System.out.println("Before Copy: " + user2.name + ", " + user2.getAge());

        copyProperties(user1, user2);

        System.out.println("After Copy: " + user2.name + ", " + user2.getAge());
    }
}

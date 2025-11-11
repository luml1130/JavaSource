package com.luml.java.Serializable;

import org.apache.commons.lang3.SerializationUtils;

/**
 * @author luml
 * @description
 * @date 2025/11/8
 */
public class SerializationUtilsTest01 {
    public static void main(String[] args) {
        // 创建对象
        Person originalPerson = new Person(25,"Bob");

        // 使用 SerializationUtils 进行深度复制
        Person clonedPerson = SerializationUtils.clone(originalPerson);

        // 检查克隆后的对象
        System.out.println("Original Person: " + originalPerson);
        System.out.println("Cloned Person: " + clonedPerson);
        /**
         * Original Person: com.luml.java.Serializable.Person@49c2faae
         * Cloned Person: com.luml.java.Serializable.Person@6267c3bb
         */
    }
}

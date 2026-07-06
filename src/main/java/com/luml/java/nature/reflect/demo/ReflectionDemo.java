package com.luml.java.nature.reflect.demo;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author luml
 * @description
 * @date 2026/7/6
 */
public class ReflectionDemo {

    /**
     * 1、获取 Class 对象的三种方式
     * 这是使用反射的第一步，所有反射操作都始于 Class 对象。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // 方式一：类名.class (编译期已知类，最安全，推荐)
        Class<?> clazz1 = User.class;

        // 方式二：对象.getClass() (已有实例时)
        User user = new User();
        Class<?> clazz2 = user.getClass();

        // 方式三：Class.forName() (运行时动态加载，常用于框架配置)
        Class<?> clazz3 = Class.forName("com.luml.java.nature.reflect.demo.User");

        // 验证：JVM中同一个类的Class对象是唯一的
        System.out.println(clazz1 == clazz2); // true
        System.out.println(clazz1 == clazz3); // true
    }

    /**
     * 2. 动态创建对象
     * 通过反射获取构造器并实例化对象。
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void create() throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = User.class;

        // 1. 调用公共无参构造
        // 注意：clazz.newInstance() 已废弃，推荐使用 Constructor
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        User user1 = (User) constructor.newInstance();
        user1.name = "Alice";
        System.out.println("User1: " + user1.name);

        // 2. 调用公共带参构造
        Constructor<?> constructorWithArgs = clazz.getConstructor(String.class, int.class);
        User user2 = (User) constructorWithArgs.newInstance("Bob", 25);
        System.out.println("User2: " + user2.name + ", Age: " + user2.getAge());

        // 3. 调用私有构造 (需要突破权限限制)
        Constructor<?> privateConstructor = clazz.getDeclaredConstructor(int.class);
        privateConstructor.setAccessible(true); // 暴力反射
        User user3 = (User) privateConstructor.newInstance(30);
        System.out.println("User3 (via private ctor): Age: " + user3.getAge());

        /**
         * User1: Alice
         * User2: Bob, Age: 25
         * User3 (via private ctor): Age: 30
         */
    }

    /**
     * 3、动态访问和修改字段
     * 可以访问公有、私有甚至静态字段。
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void FieldDemo() throws NoSuchFieldException, IllegalAccessException {
        User user = new User("Charlie", 40);
        Class<?> clazz = user.getClass();

        // 1. 访问公共字段
        Field nameField = clazz.getField("name");
        System.out.println("Original Name: " + nameField.get(user)); // Charlie

        // 修改公共字段
        nameField.set(user, "David");
        System.out.println("Modified Name: " + user.name); // David

        // 2. 访问私有字段
        Field ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true); // 必须设置，否则抛出 IllegalAccessException

        // 读取私有字段值
        int age = (int) ageField.get(user);
        System.out.println("Private Age: " + age); // 40

        // 修改私有字段值
        ageField.set(user, 45);
        System.out.println("Modified Private Age: " + user.getAge()); // 45

        // 3. 访问静态私有字段
        Field countryField = clazz.getDeclaredField("country");
        countryField.setAccessible(true);
        // 静态字段的 get/set 第一个参数传 null 或任意该类的实例均可
        System.out.println("Country: " + countryField.get(null));
    }

    /**
     * 4. 动态调用方法
     * 包括调用公有方法、私有方法以及带参数的方法。
     */
    @Test
    public void MethodDemo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User("Eve", 28);
        Class<?> clazz = user.getClass();

        // 1. 调用公共无参方法
        Method sayHelloMethod = clazz.getMethod("sayHello");
        sayHelloMethod.invoke(user); // 输出: Hello, my name is Eve

        // 2. 调用私有方法
        Method getSecretMethod = clazz.getDeclaredMethod("getSecret");
        getSecretMethod.setAccessible(true); // 突破私有限制
        Object result = getSecretMethod.invoke(user);
        System.out.println("Secret: " + result); // 输出: Secret: My age is 28

        // 3. 调用带参数的方法 (例如 setter)
        Method setAgeMethod = clazz.getMethod("setAge", int.class);
        setAgeMethod.invoke(user, 99);
        System.out.println("New Age: " + user.getAge()); // 输出: New Age: 99
    }
}

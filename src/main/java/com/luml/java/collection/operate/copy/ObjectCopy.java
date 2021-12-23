package com.luml.java.collection.operate.copy;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2021/8/5 下午11:25
 */
public class ObjectCopy {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        UserTest user1 = new UserTest("小明", 18);
        UserTest user2 = new UserTest("小红", 16);
        List<UserTest> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        System.out.println("原List：" + list);

        // 进行深度复制
//        List<UserTest> listNew = new ArrayList<>();
//        for (int i = 0; i < list.size(); i += 1) {
//            listNew.add((UserTest) list.get(i).clone());
//        }

        List<UserTest> listNew = deepCopy(list);

        System.out.println("对新list进行操作");
        for (UserTest userTest : listNew) {
            userTest.setAge(99);
        }

        System.out.println("原list" + list);
        System.out.println("新list" + listNew);

    }

    /**
     * 原List：[UserTest{id='小明', age=18}, UserTest{id='小红', age=16}]
     * 对新list进行操作
     * 原list[UserTest{id='小明', age=18}, UserTest{id='小红', age=16}]
     * 新list[UserTest{id='小明', age=99}, UserTest{id='小红', age=99}]
     */

    //关键代码 运行序列化和反序列化  进行深度拷贝
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);

        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

    class UserTest implements Serializable {

        String id;
        int age;

        public UserTest(String id, int age) {
            this.id = id;
            this.age = age;
        }

        public UserTest() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "UserTest{" +
                    "id='" + id + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}


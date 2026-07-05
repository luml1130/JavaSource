package com.luml.java.nature.Serializable.Kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.luml.domain.User;

/**
 * @author luml
 * @description
 * @date 2026/6/26
 */
public class KryoDemo {
    public static void main(String[] args) {
        // 1. 创建 Kryo 实例
        Kryo kryo = new Kryo();
        kryo.register(User.class);  // 注册类（推荐，可提升性能）

        // 2. 序列化：对象 -> 字节数组
        Output output = new Output(1024);
        //我加的
        User user1 = new User();
        kryo.writeObject(output, user1);
        byte[] bytes = output.toBytes();
        output.close();

        // 3. 反序列化：字节数组 -> 对象
        Input input = new Input(bytes);
        User user = kryo.readObject(input, User.class);
        input.close();

    }
}

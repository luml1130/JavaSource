package com.luml.java.Serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * 序列化和反序列化
 * @author lumengliang
 * @date 2018/10/19
 */
public class SerializeUtil {
    public static byte[] serizlize(Object object){
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(baos != null){
                    baos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static Object deserialize(byte[] bytes){
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try{
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
 
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}

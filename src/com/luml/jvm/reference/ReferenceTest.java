package com.luml.jvm.reference;

import com.luml.jvm.domain.User;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author luml
 * @description
 * @date 2021/8/14 下午9:43
 */
public class ReferenceTest {

    public static void main(String[] args) {

        /**
         * 1、强引用：StrongReference
         */
        User  user = new User("我是程序员 小小东邪");



        /**
         * 软引用：SoftReference
         * 场景2-1：
         * 同时被强引用与软引用，在内存紧张的情况下被GC扫描到不会进行回收
         */
        User  user1 = new User("我是程序员 小小东邪");
        SoftReference<User> softReferenceUser =  new SoftReference<>(user1);

        /**
         * 场景2-2：
         * 只有软引用，在内存紧张的情况下被GC扫描到会进行回收
         */
        SoftReference<User> softReferenceUser2 =  new SoftReference<>(new User("我是程序员 小小东邪"));




        /**
         * 弱引用：WeakReference
         * 场景3-1：同时被强引用与弱引用，在内存紧张的情况下被GC扫描到不会进行回收
         */
        User  user2 = new User("我是程序员 小小东邪");
        WeakReference<User> weakReferenceUser =  new WeakReference<>(user2);

        /**
         * 场景3-2：只有弱引用，在内存紧张的情况下被GC扫描到会进行回收
         */
        WeakReference<User> weakReferenceUser2 =  new WeakReference<>(new User("我是程序员 小小东邪"));




        /**
         * 虚引用：PhantomReference
         * 场景4-1：同时被强引用与虚引用，在内存紧张的情况下被GC扫描到不会进行回收
         * 第二个参数是引用队列：ReferenceQueue
         */
        User  user3 = new User("我是程序员 小小东邪");
        PhantomReference<User> phantomReferenceUser =  new PhantomReference<>(user3,null);

        /**
         * 场景4-2：
         * 只有虚引用等于没有引用，任何时候都可能被进行回收
         * 第二个参数是引用队列：ReferenceQueue
         */
        PhantomReference<User> phantomReferenceUser2 =  new PhantomReference<>(
                            new User("我是程序员 小小东邪"),null);


    }
}

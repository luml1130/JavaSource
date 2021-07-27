package com.luml.test.juc.lock.ReentrantLock;

/**
 * @author luml
 * @description
 * @date 2021/7/25 下午10:53
 */
public class ReentrantLockTest1 {
    public synchronized void doSomeThingA(){
        //do some thing
        doSomeThingB();
    }

    public synchronized void doSomeThingB(){
        //do some thing
    }
}

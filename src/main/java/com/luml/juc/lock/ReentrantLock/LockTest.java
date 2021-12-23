package com.luml.juc.lock.ReentrantLock;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luml
 * @description
 * @date 2021/7/19 下午9:39
 */
public class LockTest {
    public  static void main(String[] args) {
        //锁【lock.lock】必须紧跟try代码块，且unlock要放到finally第一行。
        //不要将获取锁放在try块中，因为如果获取时发生了一，异常抛出也会导致锁无故释放
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try{
            //do some thing
        }finally {
            lock.unlock();
        }
    }

}

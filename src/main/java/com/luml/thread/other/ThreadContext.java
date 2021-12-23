package com.luml.thread.other;

import com.luml.domain.User;

/**
 * @author luml
 * @description
 * @date 2021/8/18 下午10:25
 */
public class ThreadContext {
    private static ThreadLocal<User> threadLocal = new ThreadLocal<User>(){
        //匿名实现 初始化value值
        @Override
        protected User initialValue() {
            return new User("公众号：程序员阿星");
        }
    };

    /**
     * 提取value值
     * @return
     */
    public User getUser(){
        return threadLocal.get();
    }

    /**
     * 设置value值
     * @param user
     * @return
     */
    public void setUser(User user){
         threadLocal.set(user);
    }

    /**
     * 清空value
     * @param user
     */
    public void clear(User user){
        threadLocal.remove();
    }
}

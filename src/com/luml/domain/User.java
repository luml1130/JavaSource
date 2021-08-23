package com.luml.domain;

/**
 * @author luml
 * @description
 * @date 2021/8/18 下午10:26
 */
public class User {
    private String name;
    private String mobile;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /*@Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }*/

    //这个是通过IDEA自动重写的
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return mobile != null ? mobile.equals(user.mobile) : user.mobile == null;
    }
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        return result;
    }
}

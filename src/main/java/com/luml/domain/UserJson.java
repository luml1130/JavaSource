package com.luml.domain;

import lombok.Data;

/**
 * @author luml
 * @description
 * @date 2021/8/18 下午10:26
 */
@Data
public class UserJson {
    private String name;
    //private String mobile;
    private Integer id;

    public UserJson() {
    }

    public UserJson(String name) {
        this.name = name;
    }

    /*public UserJson(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }*/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }*/

    /*@Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }*/

}

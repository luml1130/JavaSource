package com.luml.java.data.json.fastjson.JSONField;

import java.util.Date;

/**
 * @author luml
 * @description
 * @date 2021/3/13 9:33 下午
 */
public class A {

    private int id;

    @JSONField(serialize=false)
    public Date date;

    @JSONField(name="ID")
    public int getId() {
        return id;
    }

    @JSONField(name="ID")
    public void setId(int value) {
        this.id = id;
    }


}

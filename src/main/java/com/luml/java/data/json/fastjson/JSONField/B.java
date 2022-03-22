package com.luml.java.data.json.fastjson.JSONField;

import java.util.Date;

/**
 * @author luml
 * @description
 * @date 2021/3/13 9:34 下午
 */
public class B {

    @JSONField(name="ID")
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int value) {
        this.id = id;
    }

    // 配置date序列化和反序列使⽤yyyyMMdd⽇期格式
    @JSONField(format="yyyyMMdd")
    public Date date;

}

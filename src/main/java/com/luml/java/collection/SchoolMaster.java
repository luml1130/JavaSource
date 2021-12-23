package com.luml.java.collection;

//import lombok.Data;

/**
 * @author luml
 * @description
 * @date 2021/8/11 下午8:44
 */
//@Data
public class SchoolMaster {
    private Integer id;
    private Long userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

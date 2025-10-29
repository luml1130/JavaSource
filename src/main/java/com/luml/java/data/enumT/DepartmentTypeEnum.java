package com.luml.java.data.enumT;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author luml
 *
 * 注意： 此处后期如果需要修改枚举值，也要同时修改表中的值uc_wxwork_data_sync_strategy
 */
@Getter
public enum DepartmentTypeEnum implements Serializable {


    CLASS(1, "班级"),
    GRADE(2, "年级"),
    SCHOOLTYPE(3, "学段"),
    ZONE(4, "校区"),
    SCHOOL(5, "学校");


    private Integer code;
    private String name;

    DepartmentTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.luml.java.data.enumT;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 对齐方式：
 * 左对齐、右对齐、居中对齐
 * @author xiqiang@e6yun.com
 * @date 2022/9/16
 */
@Getter
public enum TableAlignTypeEnum {

    /** 居左 */
    LEFT(1, "left"),
    /** 居中 */
    CENTER(2, "center"),
    /** 居右 */
    RIGHT(3, "right");

    private final int id;
    private final String desc;

    TableAlignTypeEnum(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    private static Map<Integer, TableAlignTypeEnum> ID_MAPPER = EnumSet.allOf(TableAlignTypeEnum.class).stream()
            .collect(Collectors.toMap(TableAlignTypeEnum::getId, e -> e));

    public static TableAlignTypeEnum getById(int id) {
        return ID_MAPPER.get(id);
    }
}

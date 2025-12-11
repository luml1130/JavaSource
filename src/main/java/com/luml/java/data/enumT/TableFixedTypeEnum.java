package com.luml.java.data.enumT;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 表格列字段是否固定。<br>
 * 左右滑动表格时，可令某些字段固定在表格上，可选左侧固定或右侧固定。
 * @author xiqiang@e6yun.com
 * @date 2022/9/16
 */
@Getter
public enum TableFixedTypeEnum {

    /** 固定在左侧 */
    LEFT(1, "left"),
    /** 固定在右侧 */
    RIGHT(2, "right");

    private final int id;
    private final String desc;

    TableFixedTypeEnum(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    private static Map<Integer, TableFixedTypeEnum> ID_MAPPER = EnumSet.allOf(TableFixedTypeEnum.class).stream()
            .collect(Collectors.toMap(TableFixedTypeEnum::getId, e -> e));

    public static TableFixedTypeEnum getById(int id) {
        return ID_MAPPER.get(id);
    }
}

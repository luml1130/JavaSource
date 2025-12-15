package com.luml.java.data.enumT.test;

import com.luml.java.data.enumT.DisposedTypeEnum;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2025/12/9
 */
public class EnumTest_Oper {

    @Test
    public void enum2List(){

        List<Map<String, Object>> disposedTypeMap = enumToList(DisposedTypeEnum.class);
        System.out.println(disposedTypeMap);
        //[{name=WAIT, value=0}, {name=YES, value=1}, {name=NO, value=2}, {name=BLOCK, value=3}]

        System.out.println(DisposedTypeEnum.getTypeList()); //[1, 2]
    }

    @Data
    private class DisposedType{
        Integer statusType;
        String statusName;
    }

    public static <T extends Enum<T>> List<Map<String, Object>> enumToList(Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(e -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", e.name());
                    map.put("value", e.ordinal());
                    return map;
                })
                .collect(Collectors.toList());
    }
}

package com.luml.java.collection.operate;

import cn.hutool.core.map.MapUtil;
import org.apache.commons.collections4.MapUtils;
import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * @author luml
 * @description
 * @date 2025/12/27
 */
public class NullTest {

    @Test
    public void mapNull(){
        LinkedHashMap map4 = new LinkedHashMap<String,String>();
        map4.put("1", "B");
        map4.put("3", "C");
        //org.apache.commons.collections4.MapUtils;
        System.out.println(MapUtils.isEmpty(map4));
        // cn.hutool.core.map.MapUtil;
        System.out.println(MapUtil.isEmpty(map4));
    }
}

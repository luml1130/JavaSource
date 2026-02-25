package com.luml.java.collection.operate.count;

import com.luml.domain.User2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author luml
 * @description
 * @date 2026/2/12
 */
public class ObjectOper {

    @Test
    public void test(){

        //查询电子路书修改前全部的区域列表
        List<User2> existFixedLineAreaList = new ArrayList<>();
        User2 user2 = new User2(74, "wo");
        User2 user3 = new User2(92, "ni");
        existFixedLineAreaList.add(user2);
        existFixedLineAreaList.add(user3);

        List<Integer> modifyFixedLineAreaIds = new ArrayList<>();
        modifyFixedLineAreaIds.add(74);
        modifyFixedLineAreaIds.add(92);



        //需要删除的区域
        List<User2> deleteAreaList = existFixedLineAreaList.stream()
                .filter(item -> !modifyFixedLineAreaIds.contains(item.getId()))
                .collect(toList());

        System.out.println(deleteAreaList);
    }
}

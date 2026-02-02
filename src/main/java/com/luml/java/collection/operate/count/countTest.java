package com.luml.java.collection.operate.count;

import com.google.common.collect.Sets;
import com.luml.domain.User2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * @author luml
 * @description
 * @date 2025/12/26
 */
public class countTest {

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
        List<User2> deleteAreaList = existFixedLineAreaList.stream().filter(item -> !modifyFixedLineAreaIds.contains(item.getId())).collect(toList());

        System.out.println(deleteAreaList);
    }

    @Test
    public void addAllTest(){
        Set<String> set1 = new HashSet<>();
        set1.add("1");
        set1.add("2");
        Set<String> set2 = new HashSet<>();
        set2.add("2");
        set2.add("3");
        set1.addAll(set2);
        System.out.println(set1);

    }
}

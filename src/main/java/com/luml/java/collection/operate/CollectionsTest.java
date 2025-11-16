package com.luml.java.collection.operate;

import java.util.Collections;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2025/11/16
 */
public class CollectionsTest {

    public static void main(String[] args) {
        Integer driverId = 1;
        List<Integer> list =  Collections.singletonList(driverId);
        System.out.println(list.toString());

       // List<UserListVo> failList =  Collections.synchronizedList(new ArrayList<UserListVo>());
    }
}

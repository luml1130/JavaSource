package com.luml.java.collection.operate.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2021/8/11 下午5:02
 */
public class streamTest {
    public static void main(String[] args) {
        /*List<String> a = new ArrayList<String>();
        for(String bb : a){
            System.out.println(bb);
        }*/
        List<SchoolMaster> allSchoolMasters = new ArrayList<>();
        SchoolMaster sc = new SchoolMaster();
        sc.setUserId(21213L);
        SchoolMaster sc2 = new SchoolMaster();
        sc2.setUserId(22222L);

        allSchoolMasters.add(sc);
        allSchoolMasters.add(sc2);

        List<Long> allSchoolMasterIds = allSchoolMasters.stream()
                .distinct().map(o -> o.getUserId())
                .collect(Collectors.toList());
        System.out.println(allSchoolMasterIds);
    }

    @Test
    public void test2(){
        List<Long> addRelationClassMember = null;
        addRelationClassMember.add(0L);
    }
}

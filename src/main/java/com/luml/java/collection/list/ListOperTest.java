package com.luml.java.collection.list;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author luml
 * @description
 * @date 2026/2/27
 */
public class ListOperTest {



    @Test
    public void setSubListTest(){
        Set<Integer> vehicleIds = new HashSet<>();
        vehicleIds.add(1);vehicleIds.add(2);vehicleIds.add(3);
        vehicleIds.add(4);vehicleIds.add(5);vehicleIds.add(6);
        vehicleIds.add(7);vehicleIds.add(8);vehicleIds.add(9);

        int preSize = 2; //按2个切割
        int totalVehicleIds = vehicleIds.size(); //一共9个
        //Set没有sublist方法
        /*for (int i = 0; i < totalVehicleIds; i += preSize) {
            if (i + preSize > totalVehicleIds) {
                preSize = totalVehicleIds - i;
            }
            List<Integer> subVehicleIds = //vehicleIds.subList(i, i + preSize);

            System.out.println(subVehicleIds);
        }*/
    }

    @Test
    public void subListTest(){
        System.out.println("aa");
        List<Integer> vehicleIds = new ArrayList<>();
        vehicleIds.add(1);vehicleIds.add(2);vehicleIds.add(3);
        vehicleIds.add(4);vehicleIds.add(5);vehicleIds.add(6);
        vehicleIds.add(7);vehicleIds.add(8);vehicleIds.add(9);
                //Lists.newArrayList(1,2,3,4,5,6,7,8,9);
               // new ArrayList<>(qry.getVehicleIds());

        int preSize = 2; //按2个切割
        int totalVehicleIds = vehicleIds.size(); //一共9个

        for (int i = 0; i < totalVehicleIds; i += preSize) {
            if (i + preSize > totalVehicleIds) {
                preSize = totalVehicleIds - i;
            }
            List<Integer> subVehicleIds = vehicleIds.subList(i, i + preSize);

            System.out.println(subVehicleIds);
        }
    }
}

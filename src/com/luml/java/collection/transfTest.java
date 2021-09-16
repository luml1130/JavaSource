package com.luml.java.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author luml
 * @description
 * @date 2021/9/16 下午10:31
 */
public class transfTest {

    /**
     * 数组转化为List： Arrays.asList
     */
    @Test
    public void test1(){
        String[] strArray= new String[]{"Tom", "Bob", "Jane"};
        List strList= Arrays.asList(strArray);
        System.out.println(strList);
    }

    /**
     * 数组转Set:new HashSet<>
     */
    @Test
    public void test2(){
        String[] strArray= new String[]{"Tom", "Bob", "Jane"};
        Set<String> staffsSet = new HashSet<>(Arrays.asList(strArray));

        System.out.println(staffsSet);

        staffsSet.add("Mary");
        staffsSet.remove("Tom");
        System.out.println(staffsSet);
    }

    /**
     *  List转Set: new HashSet(staffsList);
     */
    @Test
    public void test3(){
        String[] staffs = new String[]{"Tom", "Bob", "Jane"};
        List staffsList = Arrays.asList(staffs);

        Set result = new HashSet(staffsList);
        System.out.println(result);
    }

    /**
     * set转List：new ArrayList<>(staffsSet)
     */
    @Test
    public void test4(){
        String[] staffs = new String[]{"Tom", "Bob", "Jane"};
        Set<String> staffsSet = new HashSet<>(Arrays.asList(staffs));

        List<String> result = new ArrayList<>(staffsSet);
        System.out.println(result);
    }
}


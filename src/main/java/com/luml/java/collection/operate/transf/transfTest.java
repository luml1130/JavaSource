package com.luml.java.collection.operate.transf;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
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

    private static final String PHONE_GROUP_REG = " \\| ";
    private static final String PHONE_GROUP_NEW_REG = " | ";
    private static final int PHONE_LENGTH = 11;

    public static void main(String[] args) {
       /* String phoneN = "13220212021 | 40016656729 | 15920210902";
        String[] phoneNum = phoneN.trim().split(" \\| ");
        List<String> aa = Arrays.asList(phoneNum);
        List<String> bb = new ArrayList<>(phoneNum.length);
        for(String a : aa){
            bb.add(a);
        }
        System.out.println(bb);*/

        String reg = "13220212021 | 40016656729 | 15920210902";
        String[] phones = reg.split(PHONE_GROUP_REG);
        StringBuffer sb = new StringBuffer();
        for (String value : phones) {
            System.out.println("length=" + value.length());
            if (StrUtil.isNotEmpty(value) && value.length() >= PHONE_LENGTH) {
                sb.append(value).append(PHONE_GROUP_NEW_REG);
            }
        }
        System.out.println(sb.toString());
        System.out.println(sb.substring(0, sb.length() - PHONE_GROUP_NEW_REG.length()));
        //return ;
    }

    public static void main2(String[] args) {
        String phoneN = "13220212021,40016656729,15920210902";
        String[] phoneNum = phoneN.trim().split(",");

        List<String> aa = Arrays.asList(phoneNum);
        List<String> bb = new ArrayList<>(phoneNum.length);
        for(String a : aa){
            bb.add(a);
        }
        String s = StringUtils.join(bb," | ");
        System.out.println(s);
    }

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


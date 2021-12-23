package com.luml.java.develop;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2021/8/26 下午1:39
 */
public class test {
    /**
     * catch 后不用再写continue了
     * @param args
     */
    public static void main(String[] args) {
        List<String> aa = new ArrayList<>();
        aa.add("dada");
        aa.add("bbb");
        for (String deptId : aa) {
            try {
                if("dada".equals(deptId)){
                    int a = 1/0;
                }
               System.out.println(deptId);
            } catch (Exception e) {
                //log.warn(e.getMessage(), e);
                //continue;
            }
        }
    }
}

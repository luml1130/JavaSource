package com.luml;

import com.luml.juc.lock.volatileTest.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author luml
 * @description
 * @date 2021/8/23 上午11:47
 */
public class test {
    public static void main(String[] args) {

        try{
            new test().aa();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("---------");
       /* Long a = null;
        System.out.println(a == null ? 0L : a);
        System.out.println(System.currentTimeMillis());*/
        /*System.out.println("XYS".equals("XYS"));
        System.out.println("XYS".equals("xys"));
        List<String> aList = new ArrayList<>();
        for(String a : aList){
            System.out.println(a);
        }*/



        /*Boolean bb = true;
        try{
            SyncResult deptSyncResult = SyncResult.fail();
            if(deptSyncResult.isSuccess()){
                //falag = false;
            }else{
                bb = false;
            }
        }catch (Exception e){
            bb = false;
        }
        System.out.println(bb);*/

    }

    private void aa(){
        int q = 1/0;
    }
}

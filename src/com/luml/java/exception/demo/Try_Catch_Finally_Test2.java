package com.luml.java.exception.demo;

import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2021/8/29 上午10:32
 */
public class Try_Catch_Finally_Test2 {



    /*public static void main(String[] args) {
        System.out.println(test());
    }*/

    public static boolean test(){
        try{
            int a = 0/1;
            return true;
        }catch (Exception e){
            return false;
        }finally {
            System.out.println("--finally");
        }
    }

    /**
     * 会打印错误日志 并返回false
     * finally里面也会执行
     * @return
     */
    public boolean executeTest4() {
        try {
            //log.info("执行线程！");
            int a = 1/0;
            return true;
        } catch (Exception e) {
            //log.error("could not get tagName, tagId = {}, corpId = {}",2,3,e);
            return false;
            //throw new RuntimeException(e);
        }finally {
            System.out.println("我还执行吗");
        }
    }

    @Test
    public void testContinue(){
        for(int i =0;i< 3;i++){
            try{
                if(i == 1){
                    int a = 0/1;
                }
                System.out.println(i);
            }catch (Exception e){
                e.printStackTrace();
                //continue;
            }finally {
                System.out.println("--finally");
            }
        }
    }


}

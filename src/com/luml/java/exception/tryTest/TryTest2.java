package com.luml.java.exception.tryTest;

import java.util.zip.DataFormatException;

/**
 * @author luml
 * @description
 * @date 2021/3/3 9:53 上午
 */
public class TryTest2 {

    public static void main(String[] args) {
        try{
            /*System.out.println(doStuff(-1));//-1
            System.out.println(doStuff(100));//-1*/

            System.out.println(doStuff2(-1));//-1
            System.out.println(doStuff2(100));//-1
        }catch(Exception e){
            System.out.println("这里是永远都不会到达的");//
            //如果使用doStuff2的话 这里会执行 而且doStuff2(100)不会执行哦
        }
    }

    //该方法抛出受检异常
    public static int doStuff(int _p)throws Exception{
        try{
            if(_p < 0){
                throw new DataFormatException("数据格式错误");
            }else{
                return _p;
            }
        }catch(Exception e){
            //异常处理
            throw e;
        }finally{
            return -1;
        }
    }

    public static int doStuff2(int _p) throws Exception{
        if(_p < 0){
            throw new DataFormatException("数据格式错误");
        }else{
            return _p;
        }
    }
}

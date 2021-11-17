package com.luml.java.exception;

import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2021/11/8
 */
public class ExceptionTest {

    @Test
    public void a(){
        try{
            b();
        }catch (Exception e){
            System.out.println("bb");
        }
    }

    private void b(){
        int a = 1/0;
    }
}

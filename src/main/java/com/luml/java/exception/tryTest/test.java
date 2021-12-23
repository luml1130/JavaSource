package com.luml.java.exception.tryTest;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2021/9/13 下午2:47
 */
public class test {

    //private final static Logger logger = LoggerFactory.getLogger(test.class);
    private static Logger logger = Logger.getLogger(test.class.getName());

    /**
     * test：catch后不需要continue后自动会
     * @param args
     */
    public static void main(String[] args) {
        List<Long> userList = new ArrayList<>();
        userList.add(1L);
        userList.add(2L);
        for(Long userId : userList){
            try{
                if(userId == 1L){
                    int i = 1/0;
                }
                System.out.println("userId="+userId);
            }catch (Exception e){
                //logger.error("error",e);
                e.printStackTrace();
            }
        }
    }
}

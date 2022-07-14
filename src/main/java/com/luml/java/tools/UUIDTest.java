package com.luml.java.tools;

import cn.hutool.core.util.IdUtil;
import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2022/7/5
 */
public class UUIDTest {

    public static void main(String[] args) {

    }

    @Test
    public void test(){
        String objectId = IdUtil.objectId();
        System.out.println(objectId);
    }

}

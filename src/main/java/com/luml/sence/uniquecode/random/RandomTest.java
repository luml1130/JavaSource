package com.luml.sence.uniquecode.random;

import cn.hutool.core.util.RandomUtil;
import com.luml.sence.uniquecode.random.RandomUtils;
import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2026/4/17
 */
public class RandomTest {

    @Test
    public void getRandom(){
        System.out.println(RandomUtils.getRandomString(5));
        //RMKDW

    }

    //hutool
    @Test
    public void hutoolRandom(){
        System.out.println(RandomUtil.randomLong(1));
        System.out.println(RandomUtil.randomLong());
        //-8946490205122042415
        System.out.println(RandomUtil.randomString(5)); //z08u3
        System.out.println(RandomUtil.randomString("LUML",8));//5-LMLLU. 8-UMMUMLLL
        System.out.println(RandomUtil.randomStringUpper(5)); //CMZW2

    }
}

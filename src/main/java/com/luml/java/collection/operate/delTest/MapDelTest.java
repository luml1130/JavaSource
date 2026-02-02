package com.luml.java.collection.operate.delTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luml
 * @description
 * @date 2026/1/15
 */
public class MapDelTest {

    @Test
    public void delMap(){

        Map<String,Double> hashMap = new HashMap<>();

        hashMap.put( "k1", 0.1 );
        hashMap.put( "k3", 0.2 );

        Map<String,Double> hashMap2 = new HashMap<>();

        hashMap2.put( "k1", 0.1 );
        hashMap2.put( "k2", 0.2 );

        for(String key : hashMap2.keySet()){

            if(hashMap.containsKey(key)){
                hashMap.remove(key);
            }
        }
        System.out.println(hashMap);

    }
}

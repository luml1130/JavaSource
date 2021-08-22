package com.luml.java.collection.Set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author luml
 * @description
 * @date 2021/7/17 上午10:34
 */
public class SetTest {

    public static void main(String[] args) {
        Set<String> nouFoundUserSet = null;
        if(1 == 1){
            nouFoundUserSet = new HashSet<>();
            nouFoundUserSet.add("2222");
        }
        System.out.println(nouFoundUserSet);
    }

    /**
     * set集合保证不重复
     * 遍历方法没有普通for循环哦
     */
    @Test
    public void forTest(){
        Set<Integer> set=new HashSet<Integer>();
        set.add(1);
        set.add(2);
       // System.out.println(set.size());
        /*for(int i = 0;i<set.size();i++){
            System.out.println(set.);
        }*/

        for (Integer str : set) {
            System.out.println(str);
        }

        Iterator<Integer> it =  set.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }

    }
}

package com.luml.java.collection.operate.copy;


import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author luml
 * @description:
 * 深拷贝就是将A复制给B的同时，给B创建新的地址，
 * 再将地址A的内容传递到地址B。ListA与ListB内容一致，但是由于所指向的地址不同，所以改变相互不受影响。
 * @date 2021/8/5 下午11:18
 */
public class deepCopy {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        //list深度拷贝
        List<Integer> newList = new ArrayList<>();
        //CollectionUtils.addAll(newList, new Object[list.size()]);

        Collections.copy(newList, list);

        newList.set(0, 10);

        System.out.println("原list值：" + list);
        System.out.println("新list值：" + newList);
        /**
         * 原list值：[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
         * 新list值：[10, 1, 2, 3, 4, 5, 6, 7, 8, 9]
         */
    }

    /**
     * 测试深拷贝和浅拷贝：https://www.cnblogs.com/luxd/p/11933686.html
     */
    @Test
    public void testCopy(){
        List<Long> oldClassIds = new ArrayList<Long>();
        oldClassIds.add(1L);
        oldClassIds.add(3L);
        oldClassIds.add(5L);
        oldClassIds.add(7L);

        List<Long> newClassIds = new ArrayList<Long>();
        newClassIds.add(1L);
        newClassIds.add(5L);

        //下面这2个都是浅拷贝
        //List<Long> oldClassIdsTemp = oldClassIds；
        //List<Long> oldClassIdsTemp = new ArrayList<Long>();
        // oldClassIdsTemp = oldClassIds；

        List<Long> oldClassIdsTemp = new ArrayList<Long>();
        oldClassIdsTemp.addAll(oldClassIds) ;

        oldClassIds.add(0,10L);
        //oldClassIds.removeAll(newClassIds);
        //oldClassIdsTemp.add(0,10L);

        System.out.println(oldClassIds.toString());
        System.out.println(oldClassIdsTemp.toString());
    }

    @Test
    public void testCopy2(){
        List<Long> oldClassIds = new ArrayList<Long>();
        oldClassIds.add(1L);
        oldClassIds.add(3L);
        oldClassIds.add(5L);
        oldClassIds.add(7L);

        List<Long> newClassIds = new ArrayList<Long>();
        newClassIds.add(1L);
        newClassIds.add(5L);

        newClassIds.removeAll(oldClassIds);
        System.out.println(newClassIds);
    }
}

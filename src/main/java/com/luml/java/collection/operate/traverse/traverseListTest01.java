package com.luml.java.collection.operate.traverse;

import org.apache.commons.collections4.CollectionUtils;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author luml
 * @description
 *
 * 性能对比
 *  小规模数据：Collection.forEach()通常更快，因无流式处理开销 。 ‌‌
 *  大规模数据：Stream.forEach()（并行）可能更高效，尤其适合复杂操作（如过滤、映射） 。
 * @date 2025/11/16
 */
public class traverseListTest01 {


    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("bb");
        list.add("cc");


        //collectionFor(list);

        streamFor(list);

        /**
         * 迭代器
         * 可以break
         */
        /*Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("cc".equals(item)) {
                break;
            }
        }*/




    }

    /**
     * Collection.forEach() 无法break 只能通过异常抛出
     * @param list
     */
    private static void collectionFor(List<String> list){
        list.forEach(item -> {
            System.out.println(item);
        });
        try {
            list.forEach(item -> {
                System.out.println(item);
                if ("cc".equals(item)) {
                    // break;
                    try {
                        throw new Exception("brek");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            // 处理终止逻辑
        }
    }

    /**
     *  stream.forEach() 无法break 只能
     *  通过Stream的filter操作配合findFirst或anyMatch，可在满足条件时提前终止遍历。例如：
     * @param list
     */
    private  static void streamFor(List<String> list){
        /**
         * Stream.forEach()：
         * 基于流式处理，支持链式操作（如过滤、映射）， 并行需通过lambda表达式传递操作逻辑 。
         * 不能break
         */
        list.stream().forEach(item->{
            /*if("cc".equals(item)){
                //无法break
                return false;
            }*/
            System.out.println(item);
        });

        list.stream().filter(item -> {
            if ("cc".equals(item)) {
                return false; // 终止后续处理
            }
            return true;
        }).forEach(item -> {
            /* 处理逻辑 只打印了aa bb */
            System.out.println(item);
        });

    }

}



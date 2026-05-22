package com.luml.java.jdkNewFeature.jdk18.api.ParallelStream;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author luml
 * @description
 * @date 2026/5/22
 */
public class ParallelStreamDemo2 {

    @Test
    public void test(){

        /**
         * 集合数据的筛选
         * 代码使用了 parallelStream() 方法对集合数据进行筛选，即只保留长度大于 5 的字符串。使用并行流处理可以提高程序的执行效率。
         */
        List<String> list = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        List<String> filteredList = list.parallelStream()
                .filter(str -> str.length() > 5)
                .collect(Collectors.toList());
        System.out.println("filteredList="+filteredList);//filteredList=[banana, cherry, elderberry]


        /**
         * 集合数据的转换
         * 代码使用了 parallelStream() 方法对集合数据进行转换，即将集合中的每个元素平方后生成一个新的集合。使用并行流处理也可以提高程序的执行效率。
         */
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> transformedList = list2.parallelStream()
                .map(val -> val * val)
                .collect(Collectors.toList());
        System.out.println("transformedList="+transformedList);//transformedList=[1, 4, 9, 16, 25]



        /**
         *  集合数据的归约
         *  代码使用了 parallelStream() 方法对集合数据进行归约，即计算数值 1 到 10000000 的和。
         *  使用并行流处理可以更快地得到结果，因为它会将集合数据分成多个小块，分配到多个线程中进行处理。
         */
        int sum = IntStream.rangeClosed(1, 10_000_000)
                .parallel()
                .reduce(0, Integer::sum);

        System.out.println("sum="+sum);//sum=-2004260032

    }
}

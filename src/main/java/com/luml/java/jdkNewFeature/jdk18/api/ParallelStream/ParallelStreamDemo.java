package com.luml.java.jdkNewFeature.jdk18.api.ParallelStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreamDemo {

    public static void main(String[] args) {
        List<Integer> numbers = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());

        // 1. 基本用法：并行求和
        long sum = numbers.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        System.out.println("Parallel Sum: " + sum);
        //Parallel Sum: 50005000

        // 2. 错误用法演示：线程不安全的共享变量修改
        // 注意：实际生产中应避免此类写法，此处仅用于演示风险
        List<Integer> unsafeList = new ArrayList<>();
        // 这种写法可能导致数据丢失、异常或结果不一致，因为ArrayList非线程安全
        // numbers.parallelStream().forEach(unsafeList::add); 

        // 3. 正确用法一：使用无状态聚合操作（推荐）
        // 避免外部共享变量，利用Stream内部机制保证线程安全
        List<Integer> safeList = numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers count (safe): " + safeList.size());
        //Even numbers count (safe): 5000

        // 4. 正确用法二：使用原子类处理有状态操作
        AtomicInteger atomicSum = new AtomicInteger(0);
        numbers.parallelStream().forEach(atomicSum::addAndGet);
        System.out.println("Atomic Sum: " + atomicSum.get());
        //Atomic Sum: 50005000

        // 5. 正确用法三：处理对象集合并行映射
        List<String> words = Arrays.asList("java", "parallel", "stream", "performance");
        List<String> upperWords = words.parallelStream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Upper case words: " + upperWords);
        //Upper case words: [JAVA, PARALLEL, STREAM, PERFORMANCE]
    }
}

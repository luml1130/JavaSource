package com.luml.threadPool.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author luml
 * @description: 简单示例（计算 1~n 和）
 * @date 2026/4/23
 */
public class ForkJoinDemo2 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new SumTask(1, 1_000_000));//100万
        System.out.println("Sum: " + result); //Sum: 500000500000
    }

    static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 10_000; //1万
        private final long start, end;

        SumTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (long i = start; i <= end; i++) sum += i;
                return sum;
            } else {
                long mid = (start + end) / 2;
                SumTask left = new SumTask(start, mid); //1～50万
                SumTask right = new SumTask(mid + 1, end);//50万+1～100万
                left.fork(); // 异步执行左子任务
                return right.compute() + left.join(); // 同步执行右 + 等待左
            }
        }
    }
}

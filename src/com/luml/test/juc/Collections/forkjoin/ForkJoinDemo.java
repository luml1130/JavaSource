package com.luml.test.juc.Collections.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author luml
 * @description:
 * https://www.toutiao.com/i6925247981801226755
 * @date 2021/7/22 下午9:45
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
        // 创建ForkJoin池
        ForkJoinPool pool = new ForkJoinPool();
        // 结算值
        long start = 1;
        long end = 100000000000L;
        Instant startTime = Instant.now();
        RecursiveTask<Long> task = new ForkJoinSumCalculate(start, end);
        // 执行
        Long sum = pool.invoke(task);
        System.out.println("计算总和:" + sum);

        Instant endTime = Instant.now();
        System.out.println("耗时时间:" + Duration.between(startTime, endTime).toMillis());
    }

    static class ForkJoinSumCalculate extends RecursiveTask<Long> {
        private static final long serialVersionUID = 6401797500295693663L;

        // 临界值，拆分到什么时候不能在拆分
        static final long THURSHOLD = 1000L;

        private long start;

        private long end;

        public ForkJoinSumCalculate(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long length = end - start;
            // 如果小于等于临界值
            if (length <= THURSHOLD) {
                long sum = 0L;
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }else {
                long middle = (start + end) / 2;
                ForkJoinSumCalculate leftForkJoin = new ForkJoinSumCalculate(start, middle);
                // 进行拆分，同时压入线程队列
                leftForkJoin.fork();
                ForkJoinSumCalculate rigthForkJoin = new ForkJoinSumCalculate(middle + 1, end);
                // 进行拆分，同时压入线程队列
                rigthForkJoin.fork();

                // 结果合并
                return leftForkJoin.join() + rigthForkJoin.join();
            }
        }
    }
}

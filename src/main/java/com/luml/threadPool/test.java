package com.luml.threadPool;

import java.util.concurrent.Executor;

/**
 * @author luml
 * @description
 * @date 2020/4/23 9:43
 */
public class test {
    public static void main(String[] args) {

    }
    public Executor taskExecutor(){
        return null;
        /*ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("dietTaskExecutor--");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;*/
    }
}

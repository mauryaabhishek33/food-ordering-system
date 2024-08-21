package com.demo.foodordering.food_ordering.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

    @Value("${threadpool.core.size:10}")
    private int threadPoolSize;

    /**
     * This method configures and returns a ThreadPoolTaskExecutor bean.
     * The ThreadPoolTaskExecutor is a task executor that uses a thread pool to execute tasks.
     * It is configured with the core pool size which is the minimum number of threads to keep in the pool.
     * The maximum pool size is set to 20, the queue capacity is also set to 20, and the executor is configured to wait for
     * tasks to complete on shutdown.
     *
     * @return The configured ThreadPoolTaskExecutor bean.
     */
    @Bean("threadPoolExecutor")
    public TaskExecutor threadPool() {
        // Create a new instance of ThreadPoolTaskExecutor
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        // Set the core pool size which is the minimum number of threads to keep in the pool
        taskExecutor.setCorePoolSize(threadPoolSize);

        // Set the maximum pool size which is the maximum number of threads to keep in the pool
        taskExecutor.setMaxPoolSize(20);

        // Set the queue capacity which is the maximum number of tasks that can be queued
        taskExecutor.setQueueCapacity(20);

        // Set the flag to wait for tasks to complete on shutdown
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);

        // Initialize the executor
        taskExecutor.initialize();

        // Return the configured ThreadPoolTaskExecutor bean
        return taskExecutor;
    }

}

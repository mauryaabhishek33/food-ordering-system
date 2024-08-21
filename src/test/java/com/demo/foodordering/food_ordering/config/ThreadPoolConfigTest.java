package com.demo.foodordering.food_ordering.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadPoolConfigTest {

    @Test
    public void testDefaultCorePoolSize() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreadPoolConfig.class);
        TaskExecutor taskExecutor = context.getBean("threadPoolExecutor", TaskExecutor.class);
        assertEquals(10, ((ThreadPoolTaskExecutor) taskExecutor).getCorePoolSize());
    }

    @Test
    public void testCustomCorePoolSize() {
        System.setProperty("threadpool.core.size", "5");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreadPoolConfig.class);
        TaskExecutor taskExecutor = context.getBean("threadPoolExecutor", TaskExecutor.class);
        assertEquals(5, ((ThreadPoolTaskExecutor) taskExecutor).getCorePoolSize());
    }

    @Test
    public void testMaximumPoolSize() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreadPoolConfig.class);
        TaskExecutor taskExecutor = context.getBean("threadPoolExecutor", TaskExecutor.class);
        assertEquals(20, ((ThreadPoolTaskExecutor) taskExecutor).getMaxPoolSize());
    }

    @Test
    public void testQueueCapacity() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreadPoolConfig.class);
        TaskExecutor taskExecutor = context.getBean("threadPoolExecutor", TaskExecutor.class);
        assertEquals(20, ((ThreadPoolTaskExecutor) taskExecutor).getQueueCapacity());
    }


}
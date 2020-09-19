package com.learn.spring.mybatisservice;

import com.learn.spring.mybatisservice.common.cache.StaticCacheConfig;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@MapperScan("com.learn.spring.mybatisservice.dao")
@EnableAsync
@EnableCaching
public class MybatisServiceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MybatisServiceApplication.class, args);
    }

    @Bean
    public Executor taskExecutors() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
    }

    @Bean
    public List<String> cacheConfig() {
        List<String> cacheList = new ArrayList<>();
        cacheList.add("staticCacheConfig");
        cacheList.add("initializeCache");
        return cacheList;
    }

    @Bean(initMethod = "loadUserCache")
    public StaticCacheConfig userCache() {
        return new StaticCacheConfig();
    }
    @Bean(initMethod = "loadDimensionCache")
    public StaticCacheConfig dimensionCache() {
        return new StaticCacheConfig();
    }

}

/*
 * Copy right from Saurav kumar
 */

package com.learn.spring.mybatisservice.common.mybatis;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyBatisConfig {

    @Bean
    public PageInterceptor[] pageInterceptors() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "count=check");
        properties.setProperty("params", "count=countSql");
        pageInterceptor.setProperties(properties);
        return new PageInterceptor[] { pageInterceptor };
    }
}

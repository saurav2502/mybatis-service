/*
 * Copy right from Saurav kumar
 */

package com.learn.spring.mybatisservice.common.cache;

import com.learn.spring.mybatisservice.entity.IndusTypeInfo;
import com.learn.spring.mybatisservice.entity.Users;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author kumar
 * @project mybatis-service
 * @since 8/8/2020
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CacheConfigVo {
    /** logger instance of the class */
    private static final Logger log = LoggerFactory.getLogger(StaticCacheConfig.class);

    /** user cache */
    private Map<String, Users> userCache;

    /** dimension cache */
    private Map<String, IndusTypeInfo> indusTypeCache;

    public Map<String, Users> getUserCache() {
        return userCache;
    }

    public void setUserCache(Map<String, Users> userCache) {
        this.userCache = userCache;
    }

    public Map<String, IndusTypeInfo> getIndusTypeCache() {
        return indusTypeCache;
    }

    public void setIndusTypeCache(Map<String, IndusTypeInfo> indusTypeCache) {
        this.indusTypeCache = indusTypeCache;
    }

    public CacheConfigVo() {}

    public CacheConfigVo(Map<String, Users> userCache, Map<String, IndusTypeInfo> indusTypeCache) {
        this.userCache = userCache;
        this.indusTypeCache = indusTypeCache;
    }

    /**
     * @{utility method}
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CacheConfigVo{");
        stringBuilder.append("loadUserCache=" + userCache);
        stringBuilder.append(", indusTypeCache=" + indusTypeCache);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public Map<String, Users> getIndustryType() {
        return getIndustryType();
    }
}

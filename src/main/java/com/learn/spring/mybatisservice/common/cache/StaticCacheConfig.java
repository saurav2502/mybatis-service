/*
 * Copy right from Saurav kumar
 */

package com.learn.spring.mybatisservice.common.cache;

import static java.util.stream.Collectors.toMap;

import com.learn.spring.mybatisservice.dao.UserDao;
import com.learn.spring.mybatisservice.dao.UserInfoDao;
import com.learn.spring.mybatisservice.entity.IndusTypeInfo;
import com.learn.spring.mybatisservice.entity.Users;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author kumar
 * @project mybatis-service
 * @since 8/8/2020
 */
@Component(value = "staticCacheConfig")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StaticCacheConfig {
    /** logger instance of the class */
    private static final Logger log = LoggerFactory.getLogger(StaticCacheConfig.class);

    Map<String, Users> usersMap = null;

    Map<String, IndusTypeInfo> indusTypeInfoMap = null;

    @Autowired private UserDao userDao;

    @Autowired private UserInfoDao userInfoDao;

    @Order(1)
    public boolean loadUserCache() {
        log.info("userConfigCache invoked successfully...");
        List<Users> userList = null;
        CacheConfigVo cacheConfigVo = null;
        try {
            userList = userDao.findAllUserData();
            if (!CollectionUtils.isEmpty(userList)) {
                usersMap = new ConcurrentHashMap<>();
                usersMap = userList.stream().collect(toMap(Users::getUserID, userdata -> userdata));
                if (!CollectionUtils.isEmpty(usersMap)) {
                    cacheConfigVo = new CacheConfigVo();
                    cacheConfigVo.setUserCache(usersMap);
                }
            }
        } catch (Exception e) {
            log.error("loadUserCache getting error with DB", e.getMessage());
            return false;
        }
        log.info("Logging dimension map data {}", String.valueOf(usersMap));
        log.info("Returning from userConfigCache ");
        return true;
    }

    @PostConstruct()
    @Order(0)
    public boolean loadDimensionCache() {
        log.info("loadDimensionCache invoked successfully...");
        CacheConfigVo cacheConfigVo = null;
        List<IndusTypeInfo> indusTypeInfos = null;
        try {
            indusTypeInfos = userInfoDao.getAllIndustry();
            if (!CollectionUtils.isEmpty(indusTypeInfos)) {
                indusTypeInfoMap = new ConcurrentHashMap<>();
                indusTypeInfoMap =
                        indusTypeInfos.stream()
                                .collect(
                                        toMap(
                                                IndusTypeInfo::getIndusTypeName,
                                                industryInfo -> industryInfo));
                if (!CollectionUtils.isEmpty(indusTypeInfoMap)) {
                    cacheConfigVo = new CacheConfigVo();
                    cacheConfigVo.setIndusTypeCache(indusTypeInfoMap);
                }
            }
        } catch (Exception e) {
            log.error("loadDimensionCache failed to load data into cache..." + e.getMessage());
            return false;
        }
        log.info("Logging dimension map data {}", String.valueOf(indusTypeInfoMap));
        log.info("Returning from loadDimensionCache ");
        return true;
    }

    public Map<String, Users> getUsersCache() {
        return usersMap;
    }

    public Map<String, IndusTypeInfo> getIndusTypeCache() {
        return indusTypeInfoMap;
    }
}

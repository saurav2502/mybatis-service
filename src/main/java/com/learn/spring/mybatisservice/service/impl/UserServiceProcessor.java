package com.learn.spring.mybatisservice.service.impl;

import com.learn.spring.mybatisservice.dao.UserInfoDao;
import com.learn.spring.mybatisservice.entity.UserInfoData;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * async Thread to insert user to db
 *
 * @author kumar
 * @project mybatis-service
 * @since 8/1/2020
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserServiceProcessor implements Callable<Boolean> {
    /** logger processor */
    private static final Logger logger = LoggerFactory.getLogger(UserServiceProcessor.class);

    /** userInfo class for thread processor */
    private UserInfoData userInfo;

    /**
     * constructor
     *
     * @param userInfo
     */
    public UserServiceProcessor(UserInfoData userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * DAO for inserting records
     * @insert
     */
    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * {@overriden call method}
     *
     * @return
     * @throws Exception
     */
    @Override
    public Boolean call() throws Exception {
        logger.info("Entering into UserServiceProcessor:: call() -> {}", userInfo.toString());
        int insertCount = 0;
        try {
            insertCount = userInfoDao.insert(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean result = false;
        if (insertCount > 0) {
            result = true;
        }
        logger.info("Returning from UserServiceProcessor:: call() with Result: -> {}", result);
        return result;
    }
}

package com.learn.spring.mybatisservice.service.impl;

import com.learn.spring.mybatisservice.entity.UserInfoData;
import com.learn.spring.mybatisservice.entity.Users;
import com.learn.spring.mybatisservice.response.ResultResponse;
import com.learn.spring.mybatisservice.service.UserAddService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author kumar
 * @project mybatis-service
 * @since 8/1/2020
 */
@Service
public class UserAddServiceImpl implements UserAddService {
    /** logger added as a service logger */
    private static final Logger logger = LoggerFactory.getLogger(UserAddServiceImpl.class);

    @Autowired private WebApplicationContext applicationContext;

    /**
     * add user info
     *
     * @param usersVo
     * @return
     */
    @Override
    public ResultResponse<Users> addUsers(List<Users> usersVo) throws Exception {
        logger.info(
                "Entering into UserAddServiceImpl:: addUsers with userVo {}", usersVo.toString());
        if (CollectionUtils.isEmpty(usersVo)) {
            throw new Exception("body can not be empty..");
        }
        ExecutorService executorService = Executors.newFixedThreadPool(usersVo.size());
        UserInfoData infoData = null;
        UserServiceProcessor serviceProcessor = null;
        List<Future<Boolean>> futureList = new ArrayList<>();
        Future<Boolean> future = null;
        for (Users user : usersVo) {
            infoData =
                    new UserInfoData(
                            user.getUserID(),
                            user.getUserName(),
                            user.getUserEmail(),
                            user.getUserContact());
            serviceProcessor =
                    (UserServiceProcessor)
                            applicationContext.getBean("userServiceProcessor", infoData);
            future = executorService.submit(serviceProcessor);
            futureList.add(future);
        }
        executorService.shutdown();
        boolean result = false;
        for (Future futureResult : futureList) {
            Boolean bool = (Boolean) futureResult.get();
            result = bool.booleanValue();
            if (!result) {
                return new ResultResponse<Users>('F', "failed", HttpStatus.CREATED, null);
            }
        }

        return new ResultResponse<Users>('S', "success", HttpStatus.CREATED, null);
    }
}

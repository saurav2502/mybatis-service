package com.learn.spring.mybatisservice.service.impl;

import static java.util.stream.Collectors.toMap;

import com.learn.spring.mybatisservice.dao.UserInfoDao;
import com.learn.spring.mybatisservice.entity.IndusTypeInfo;
import com.learn.spring.mybatisservice.entity.UserInfoData;
import com.learn.spring.mybatisservice.entity.Users;
import com.learn.spring.mybatisservice.response.ResultResponse;
import com.learn.spring.mybatisservice.service.UserAddService;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.jetbrains.annotations.NotNull;
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

    public static final String USER_CONTACT = "7870459937";

    @Autowired
    private WebApplicationContext applicationContext;

    /**
     * DAO for inserting records
     *
     * @insert
     */
    @Autowired private UserInfoDao userInfoDao;

    /**
     * add user info
     *
     * @param usersVo
     * @return
     */
    @Override
    public ResultResponse<Users> addUsers(List<Users> usersVo) throws Exception {
        logger.info(
                "Entering into UserAddServiceImpl:: addUsers with userVo {}", usersVo);
        if (CollectionUtils.isEmpty(usersVo)) {
            throw new Exception("body can not be empty..");
        }
        List<Future<Boolean>> futureList = getFuturesImage(usersVo);
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

    @NotNull
    private List<Future<Boolean>> getFuturesImage(List<Users> usersVo) {
        ExecutorService executorService = Executors.newFixedThreadPool(usersVo.size());
        UserInfoData infoData = null;
        UserServiceProcessor serviceProcessor = null;
        List<Future<Boolean>> futureList = new ArrayList<>();
        Future<Boolean> future = null;
        for (Users user : usersVo) {
            MessageFormat mf =
                    new MessageFormat(
                            "getUserContact: {0}, getUserEmail: {1}, getUserID: {2}, getUserID: {3}");
            System.out.println(
                    mf.format(
                            new Object[] {
                                user.getUserContact(),
                                user.getUserEmail(),
                                user.getUserName(),
                                user.getUserID()
                            }));
            logger.info(
                    "message formatter: >> ",
                    mf.format(
                            new Object[] {
                                user.getUserContact(),
                                user.getUserEmail(),
                                user.getUserName(),
                                user.getUserID()
                            }));
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
        return futureList;
    }

    /**
     * {@utility method}
     *
     * @return
     */
    @Override
    public Map<Integer, IndusTypeInfo> getAllIndustry() {
        List<IndusTypeInfo> indusTypeInfos = null;
        Map<Integer, IndusTypeInfo> indusTypeInfoMap = null;
        try {
            indusTypeInfos = userInfoDao.getAllIndustry();
            if (!CollectionUtils.isEmpty(indusTypeInfos)) {
                indusTypeInfoMap = new HashMap<>();
                indusTypeInfoMap =
                        indusTypeInfos.stream()
                                .collect(
                                        toMap(
                                                IndusTypeInfo::getIndusTypeId,
                                                indusTypeInfo -> indusTypeInfo));
                /*industryInfo -> industryInfo,
                (oldvalue, newValue) -> oldvalue, ConcurrentHashMap::new));*/
            }
            if (!CollectionUtils.isEmpty(indusTypeInfoMap)) {
                return indusTypeInfoMap;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * {@utility methods}
     *
     * @param users
     * @return
     */
    @Override
    public int getGeneratedKey(Users users) {
        users.setUserContact(USER_CONTACT);
        int counter;
        try {
            counter = userInfoDao.getGeneratedKey(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users.getID();
    }
}

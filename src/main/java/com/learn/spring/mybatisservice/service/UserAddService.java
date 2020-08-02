package com.learn.spring.mybatisservice.service;

import com.learn.spring.mybatisservice.entity.Users;
import com.learn.spring.mybatisservice.response.ResultResponse;
import java.util.List;

/**
 * user service to add user
 *
 * @author kumar
 * @project mybatis-service
 * @since 8/1/2020
 */
public interface UserAddService {
    /**
     * addUsers to add user
     * @param usersVo
     * @return
     */
    ResultResponse<Users> addUsers(List<Users> usersVo) throws Exception;
}

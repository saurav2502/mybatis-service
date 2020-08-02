package com.learn.spring.mybatisservice.service;

import com.learn.spring.mybatisservice.entity.User;
import com.learn.spring.mybatisservice.entity.UserInfo;
import com.learn.spring.mybatisservice.response.PageResult;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface UserService {
    Map<String, String> saveUserInfo(UserInfo userInfo) throws Exception;

    UserInfo getUserInfo(UserInfo user);

    /**
     * @author saurav
     * @description : to returns all user
     * @date 21/06/2020
     * @param pageSize
     * @param pageNum
     * @return pageResult
     * @throws Exception
     */
    PageResult<UserInfo> findAllUser(int pageSize, int pageNum)throws Exception;

    /**
     * @author saurav
     * @param userId
     * @return userinfo
     */
    UserInfo findUserById(Long userId);

    /**
     * @author saurav
     * @param userIds
     * @return userinfo
     */
    List<UserInfo> findUserInfo(List<Long> userIds);

    CompletableFuture<User> findUserAsync(String user) throws InterruptedException;

    List<String> findImageUrls(List<String> imageIds);

    int updateDB(Map<Integer, List<String>> maps);

}

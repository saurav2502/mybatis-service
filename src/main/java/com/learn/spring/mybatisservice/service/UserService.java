package com.learn.spring.mybatisservice.service;

import com.learn.spring.mybatisservice.entity.UserInfo;

import java.util.Map;

public interface UserService {
    Map<String, String> saveUserInfo(UserInfo userInfo) throws Exception;

    UserInfo getUserInfo(UserInfo user);
}

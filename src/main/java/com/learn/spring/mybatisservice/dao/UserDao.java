package com.learn.spring.mybatisservice.dao;

import com.learn.spring.mybatisservice.entity.UserInfo;

import java.util.List;

public interface UserDao {
    int saveUserInfo(UserInfo userInfo);

    UserInfo getUserInfo(UserInfo user);

    List<UserInfo> findAllUser();
}

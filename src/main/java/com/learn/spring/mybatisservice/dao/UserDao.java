package com.learn.spring.mybatisservice.dao;

import com.learn.spring.mybatisservice.entity.UserInfo;

public interface UserDao {
    int saveUserInfo(UserInfo userInfo);

    UserInfo getUserInfo(UserInfo user);
}

package com.learn.spring.mybatisservice.dao;

import com.learn.spring.mybatisservice.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int saveUserInfo(UserInfo userInfo);

    UserInfo getUserInfo(UserInfo user);

    List<UserInfo> findAllUser();
}

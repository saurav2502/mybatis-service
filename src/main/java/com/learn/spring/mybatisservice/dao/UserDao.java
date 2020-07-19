package com.learn.spring.mybatisservice.dao;

import com.learn.spring.mybatisservice.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    int saveUserInfo(UserInfo userInfo);

    UserInfo getUserInfo(UserInfo user);

    List<UserInfo> findAllUser();

    UserInfo findUserById(@Param("id") Long userId);

    List<UserInfo> findUserInfo(@Param("ids") List<Long> userIds);

    UserInfo getUserData(@Param("id") Long userId);

    List<String> getImageUrls(@Param("imageIds") List<String> imageIds);
}

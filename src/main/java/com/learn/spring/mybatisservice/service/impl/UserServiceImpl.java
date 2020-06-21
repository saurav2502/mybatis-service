package com.learn.spring.mybatisservice.service.impl;

import com.learn.spring.mybatisservice.dao.UserDao;
import com.learn.spring.mybatisservice.entity.UserInfo;
import com.learn.spring.mybatisservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final String ret_succ_msg = "Success";
    private static final String ret_failure_msg = "fail";
    private static final String ret_succ_code = "200";
    private static final String ret_failure_code = "99";
    private static final String ret_exception_message = "SQL fail to insert";
    private static final String ret_exception_code = "SQL TRACE 1099";

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, String> saveUserInfo(UserInfo userInfo) throws Exception {
        Map<String, String>ret = new HashMap<>();
        int counter = 0;
        try {
            counter = userDao.saveUserInfo(userInfo);
            if (counter > 0) {
                ret.put("message",ret_succ_msg);
                ret.put("status",ret_succ_code);
            }else {
                ret.put("message",ret_failure_msg);
                ret.put("status",ret_failure_code);
            }
        } catch (Exception e) {
            ret.put("message",ret_exception_message);
            ret.put("status",ret_exception_code);
        }

        return ret;
    }

    @Override
    public UserInfo getUserInfo(UserInfo user) {
        return userDao.getUserInfo(user);
    }
}

package com.learn.spring.mybatisservice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.spring.mybatisservice.dao.UserDao;
import com.learn.spring.mybatisservice.entity.UserInfo;
import com.learn.spring.mybatisservice.response.PageResult;
import com.learn.spring.mybatisservice.response.PageVO;
import com.learn.spring.mybatisservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String ret_succ_msg = "Success";
    private static final String ret_failure_msg = "fail";
    private static final String ret_succ_code = "200";
    private static final String ret_failure_code = "99";
    private static final String ret_exception_message = "SQL fail to insert";
    private static final String ret_exception_code = "SQL TRACE 1099";

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Map<String, String> saveUserInfo(UserInfo userInfo) throws Exception {
        Map<String, String>ret = new HashMap<>();
        int counter;
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

    /**
     * author saurav
     * description : to returns all user
     * date 21/06/2020
     * @param pageSize page size
     * @param pageNum page number
     * @return all users
     */
    @Override
    public PageResult<UserInfo> findAllUser(int pageSize, int pageNum) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfos = new ArrayList<>() ;
        try {
            userInfos = userDao.findAllUser();
        }catch (Exception e) {
            logger.info("Find User queries failed");
        }
        PageVO pageVO = new PageVO();
        pageVO.setPageCurr(pageNum);
        pageVO.setPageSize(pageSize);
        pageVO.setStartPage(page.getStartRow()+1);
        pageVO.setEndpage(page.getEndRow());
        pageVO.setTotalRows(page.getTotal());
        pageVO.setTotalPages(page.getPages());
        PageResult<UserInfo> pageResult = new PageResult<>(userInfos,pageVO,UserInfo.class);
        return pageResult;
    }
}

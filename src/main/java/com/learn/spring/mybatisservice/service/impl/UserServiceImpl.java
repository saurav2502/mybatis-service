package com.learn.spring.mybatisservice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.spring.mybatisservice.dao.UserDao;
import com.learn.spring.mybatisservice.entity.User;
import com.learn.spring.mybatisservice.entity.UserInfo;
import com.learn.spring.mybatisservice.response.PageResult;
import com.learn.spring.mybatisservice.response.PageVO;
import com.learn.spring.mybatisservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final RestTemplate restTemplate;

    private static final String ret_succ_msg = "Success";
    private static final String ret_failure_msg = "fail";
    private static final String ret_succ_code = "200";
    private static final String ret_failure_code = "99";
    private static final String ret_exception_message = "SQL fail to insert";
    private static final String ret_exception_code = "SQL TRACE 1099";

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(RestTemplateBuilder restTemplate, UserDao userDao) {
        this.restTemplate = restTemplate.build();
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
        PageVO pageVO = getPageVO(pageSize, pageNum, page);
        PageResult<UserInfo> pageResult = new PageResult<>(userInfos,pageVO,UserInfo.class);
        return pageResult;
    }

    @NotNull
    private PageVO getPageVO(int pageSize, int pageNum, Page page) {
        PageVO pageVO = new PageVO();
        pageVO.setPageCurr(pageNum);
        pageVO.setPageSize(pageSize);
        pageVO.setStartPage(page.getStartRow()+1);
        pageVO.setEndpage(page.getEndRow());
        pageVO.setTotalRows(page.getTotal());
        pageVO.setTotalPages(page.getPages());
        return pageVO;
    }

    /**
     * @param userId
     * @return userinfo
     * @author saurav
     */
    @Override
    public UserInfo findUserById(Long userId) {
        //return userDao.findUserById(userId);
        return userDao.getUserData(userId);
    }

    /**
     * @param userIds
     * @return userinfo
     * @author saurav
     */
    @Override
    public List<UserInfo> findUserInfo(List<Long> userIds) {
        return userDao.findUserInfo(userIds);
    }

    @Override
    @Async
    public CompletableFuture<User> findUserAsync(String user) throws InterruptedException {
        logger.info("Lookup " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        User results = restTemplate.getForObject(url, User.class);
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }
}

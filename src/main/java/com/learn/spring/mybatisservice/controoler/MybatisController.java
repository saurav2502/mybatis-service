package com.learn.spring.mybatisservice.controoler;

import com.learn.spring.mybatisservice.entity.UserInfo;
import com.learn.spring.mybatisservice.response.PageResult;
import com.learn.spring.mybatisservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mybatis-service")
public class MybatisController {

    private static final Logger logger = LoggerFactory.getLogger(MybatisController.class);

    private UserService userService;

    @Autowired
    public MybatisController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUserInfo")
    public UserInfo getUserInfo(final UserInfo user) {
        return userService.getUserInfo(user);
    }

    @PostMapping("/saveUserInfo")
    public Map<String, String> saveUserInfo(@RequestBody final UserInfo userInfo) throws Exception {
        logger.info("UserInfo: {}", userInfo.toString());
        return userService.saveUserInfo(userInfo);
    }

    @GetMapping("/findAllUser/{pageSize}/{pageNum}")
    public PageResult<UserInfo> findAllUser(@PathVariable final int pageSize, @PathVariable final int pageNum) throws Exception {
        return userService.findAllUser(pageSize,pageNum);
    }

    @GetMapping("/findUserById")
    public UserInfo findUserById (@RequestParam(value = "userId", required = true) final Long userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/findUserInfo")
    public List<UserInfo> findUserInfo (@RequestParam("userIds") List<Long> userIds) {
        return userService.findUserInfo(userIds);
    }
}

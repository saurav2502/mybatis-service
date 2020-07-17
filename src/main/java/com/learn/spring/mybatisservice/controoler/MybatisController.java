package com.learn.spring.mybatisservice.controoler;

import com.learn.spring.mybatisservice.common.exception.IdNotFoundException;
import com.learn.spring.mybatisservice.entity.User;
import com.learn.spring.mybatisservice.entity.UserInfo;
import com.learn.spring.mybatisservice.response.PageResult;
import com.learn.spring.mybatisservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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
        if (user.getUserId() == null) {
            throw new IdNotFoundException("userId :" + user.getUserId());
        }
        UserInfo vo = userService.getUserInfo(user);
        //int httpErrorCode = getErrorCode(httpRequest);
        return vo;
    }

    /**
     * @param
     * @param
     * @return
     * @author Saurav Kumar
     * @since 7/9/2020
     */
    @PostMapping("/saveUserInfo")
    public Map<String, String> saveUserInfo(@RequestBody final UserInfo userInfo) throws Exception {
        logger.info("UserInfo: {}", userInfo.toString());
        return userService.saveUserInfo(userInfo);
    }

    @GetMapping("/findAllUser/{pageSize}/{pageNum}")
    public PageResult<UserInfo> findAllUser(@PathVariable  final int pageSize, @PathVariable final int pageNum) throws Exception {
        return userService.findAllUser(pageSize,pageNum);
    }

    @GetMapping("/findUserById")
    public UserInfo findUserById (@RequestParam(value = "userId", required = true) final Long userId) {
        UserInfo info = userService.findUserById(userId);
        if (info == null)
            throw new IdNotFoundException("UserId: "+ userId);
        return info;
    }

    @GetMapping("/findUserInfo")
    public List<UserInfo> findUserInfo (@RequestParam("userIds") List<Long> userIds) {
        return userService.findUserInfo(userIds);
    }

    @GetMapping("/zip")
    public void zipList() {
        List<String> list1 = List.of("java-8","java-11","java-14");
        List<Integer> list2 = List.of(2014,2018,2020);
    }

    @GetMapping("/async")
    public CompletableFuture<User>findUserAsync(@RequestParam final String user) throws Exception {
        CompletableFuture<User> page1 = userService.findUserAsync("PivotalSoftware");
        CompletableFuture<User> page2 = userService.findUserAsync("CloudFoundry");
        CompletableFuture<User> page3 = userService.findUserAsync("Spring-Projects");
        CompletableFuture.allOf(page1,page2,page3).join();
        logger.info("--> " + page1.get());
        logger.info("--> " + page2.get());
        logger.info("--> " + page3.get());
        return userService.findUserAsync(user);
    }
}

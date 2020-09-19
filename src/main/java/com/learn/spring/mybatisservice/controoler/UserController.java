package com.learn.spring.mybatisservice.controoler;

import com.learn.spring.mybatisservice.common.cache.CacheConfigVo;
import com.learn.spring.mybatisservice.entity.IndusTypeInfo;
import com.learn.spring.mybatisservice.entity.Users;
import com.learn.spring.mybatisservice.response.ResultResponse;
import com.learn.spring.mybatisservice.service.UserAddService;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller to record User based request
 *
 * @author kumar
 * @project mybatis-service
 * @since 8/1/2020
 */
@RestController
public class UserController {

  /** logger added to record logs */
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  /** userservice impl to record */
  @Autowired private UserAddService userAddService;

  @Autowired
  private CacheConfigVo configVo;

  @PostMapping("/v1/addUsers")
  public ResponseEntity<ResultResponse<Users>> addUsers(@RequestBody final List<Users> usersVo)
      throws Exception {
    logger.info("Entering into addUsers service with userVo: {}", usersVo);
    ResultResponse<Users> userInfo = userAddService.addUsers(usersVo);
    return ResponseEntity.ok(userInfo);
  }

  @GetMapping("/getAllIndustry")
  public Map<Integer, IndusTypeInfo> getAllIndustry() {
    return userAddService.getAllIndustry();
  }

  @GetMapping("getGeneratedKey")
  public int getGeneratedKey(final Users users) {
    return userAddService.getGeneratedKey(users);
  }

  @GetMapping("/userCache")
  public ResponseEntity<Map<String, Users>> getUserCache () {
    return ResponseEntity.ok(configVo.getUserCache());
  }
}

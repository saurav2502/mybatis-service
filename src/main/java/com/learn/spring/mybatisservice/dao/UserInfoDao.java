package com.learn.spring.mybatisservice.dao;

import com.learn.spring.mybatisservice.entity.UserInfoData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author kumar
 * @project mybatis-service
 * @since 8/2/2020
 */
@Repository
public interface UserInfoDao {
    /**
     * inserting to user_info_t
     *
     * @param userInfo
     * @return
     */
    int insert(@Param("userInfo") UserInfoData userInfo);
}

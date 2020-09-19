package com.learn.spring.mybatisservice.dao;

import com.learn.spring.mybatisservice.entity.IndusTypeInfo;
import com.learn.spring.mybatisservice.entity.UserInfoData;
import com.learn.spring.mybatisservice.entity.Users;
import java.util.List;
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

    /**
     * returning all industry
     *
     * @return
     */
    List<IndusTypeInfo> getAllIndustry();

    /**
     * getGeneratedKey
     * @param users
     * @return
     */
    int getGeneratedKey(Users users);
}

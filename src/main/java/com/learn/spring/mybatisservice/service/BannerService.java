package com.learn.spring.mybatisservice.service;

import com.learn.spring.mybatisservice.entity.BannerXmlVO;
import com.learn.spring.mybatisservice.response.ResultResponse;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBException;

/**
 * Banner business logics
 * @project mybatis-service
 * @uthor kumar
 * @since 8/1/2020
 */
public interface BannerService {
    ResultResponse<BannerXmlVO> generateBanner(BannerXmlVO banner) throws JAXBException, FileNotFoundException;
}

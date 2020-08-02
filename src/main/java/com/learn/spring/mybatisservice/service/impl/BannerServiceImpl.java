package com.learn.spring.mybatisservice.service.impl;

import com.learn.spring.mybatisservice.entity.BannerXmlVO;
import com.learn.spring.mybatisservice.entity.DimensionVO;
import com.learn.spring.mybatisservice.response.ResultResponse;
import com.learn.spring.mybatisservice.service.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kumar
 * @project mybatis-service
 * @since 8/1/2020
 */
@Service
public class BannerServiceImpl implements BannerService {

    /**
     * logger to keep track of services execution.
     */
    private static final Logger logger = LoggerFactory.getLogger(BannerServiceImpl.class);

    @Value("${outputPath}")
    String outputPath;

    /**
     * get the banner xml
     *
     * @param banner
     * @return
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    @Override
    public ResultResponse<BannerXmlVO> generateBanner(BannerXmlVO banner) throws JAXBException, FileNotFoundException {
        logger.info("Entering into generateBanner -> BannerXmlVO {}", banner.toString());
        if (!ObjectUtils.isEmpty(banner)) {
            banner.setOutputPath(outputPath);
            List<DimensionVO> dimesion = new ArrayList<>();
            for (DimensionVO vo : banner.getElements()) {
                vo.setImageUrl(banner.getImageUrl());
                vo.setLogoUrls(banner.getImageLogo());
                vo.setMainTitle(banner.getHeadings().getMainText());
                vo.setSubTitle(banner.getHeadings().getSubtitle());
                vo.setDescription(banner.getHeadings().getSummary());
                dimesion.add(vo);
            }
            banner.setElements(dimesion);
            /*
             * banner.setImageLogo(null); banner.setImageUrl(null);
             */

            JAXBContext contextObj = null;
            try {
                contextObj = JAXBContext.newInstance(BannerXmlVO.class);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // Print XML String to Console
            StringWriter sw = new StringWriter();

            // Write XML to StringWriter
            marshallerObj.marshal(banner, sw);
            // Verify XML Content
            String xmlContent = sw.toString();
            System.out.println(xmlContent);
            marshallerObj.marshal(banner, new FileOutputStream("banner.xml"));
            File file = new File(outputPath);
            if (file.exists()) {

            } else {
                System.out.printf("File dir does not exists");
            }
        }
        return new ResultResponse<BannerXmlVO>('S', "Success", HttpStatus.OK, banner);
    }
}

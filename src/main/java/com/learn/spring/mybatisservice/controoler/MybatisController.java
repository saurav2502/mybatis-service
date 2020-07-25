package com.learn.spring.mybatisservice.controoler;

import com.learn.spring.mybatisservice.common.exception.IdNotFoundException;
import com.learn.spring.mybatisservice.entity.BannerXmlVO;
import com.learn.spring.mybatisservice.entity.DimensionVO;
import com.learn.spring.mybatisservice.entity.User;
import com.learn.spring.mybatisservice.entity.UserInfo;
import com.learn.spring.mybatisservice.response.PageResult;
import com.learn.spring.mybatisservice.response.ResultResponse;
import com.learn.spring.mybatisservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/mybatis-service")
public class MybatisController {

    private static final Logger logger = LoggerFactory.getLogger(MybatisController.class);

    private UserService userService;

    @Value("${outputPath}")
    String outputPath;

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

    @Cacheable(value = "user", key = "#userId", unless = "#result.userId < 12000")
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
    private static final String EXTENSION = ".docx";
    private static final String SERVER_LOCATION = "C:/Users/Saurav Kumar/OneDrive";

    @GetMapping("/download")
    public ResponseEntity fileDownload(@RequestParam("file1") String file1, HttpServletResponse response) throws IOException {

        /*File file = new File(SERVER_LOCATION + File.separator + file1 + EXTENSION);
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=fil1.docx ");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);*/
        File file = new File(SERVER_LOCATION+ File.separator + file1 + EXTENSION);
        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (null==mimeType) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition",String.format("inline; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/zipDownload")
    public ResponseEntity zipDownload (@RequestParam("imageIds") final List<String> imageIds, HttpServletResponse response )throws Exception {
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        if (!ObjectUtils.isEmpty(imageIds)) {
            List<String>imageUrls = userService.findImageUrls(imageIds);
            if (!ObjectUtils.isEmpty(imageUrls)) {
                for (String imageUrl : imageUrls) {
                    FileSystemResource resource = new FileSystemResource(imageUrl);
                    ZipEntry zipEntry = new ZipEntry(resource.getFilename());
                    zipEntry.setSize(resource.contentLength());
                    zipOut.putNextEntry(zipEntry);
                    StreamUtils.copy(resource.getInputStream(), zipOut);
                    zipOut.closeEntry();
                }
                zipOut.finish();
                zipOut.close();
                response.setStatus(HttpServletResponse.SC_OK);
                response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "zipFileName" + "\"");
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/banner/{bannerId}")
    public ResultResponse<BannerXmlVO> getBanner(@RequestBody BannerXmlVO banner, @PathVariable("bannerId") Integer bannerId) throws JAXBException, FileNotFoundException {
        if (!ObjectUtils.isEmpty(banner)){
            banner.setOutputPath(outputPath);
            List<DimensionVO>dimesion = new ArrayList<>();
            for (DimensionVO vo: banner.getElements()){
                vo.setImageUrl(banner.getImageUrl());
                vo.setLogoUrls(banner.getImageLogo());
                vo.setMainTitle(banner.getHeadings().getMainText());
                vo.setSubTitle(banner.getHeadings().getSubtitle());
                vo.setDescription(banner.getHeadings().getSummary());
                dimesion.add(vo);
            }
            banner.setElements(dimesion);
            /*banner.setImageLogo(null);
            banner.setImageUrl(null);*/

            JAXBContext contextObj = null;
            try {
                contextObj = JAXBContext.newInstance(BannerXmlVO.class);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            marshallerObj.marshal(banner, sw);
            //Verify XML Content
            String xmlContent = sw.toString();
            System.out.println( xmlContent );
            marshallerObj.marshal(banner, new FileOutputStream("banner.xml"));
            File file = new File(outputPath);
            if (file.exists()) {

            }else {
                System.out.printf("File dir does not exists");
            }
        }
        return new ResultResponse<BannerXmlVO>('S',"Success", HttpStatus.OK,banner);
    }
}

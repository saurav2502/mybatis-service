package com.learn.spring.mybatisservice.entity;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @project mybatis-service
 * @uthor kumar
 * @since 7/25/2020
 */
public class DimensionVO {
    private String dimensionName;
    private Integer width;
    private Integer height;
    private String mainTitle;
    private String subTitle;
    private String description;
    private String inputPath;
    private String imageUrls;
    private String logoUrls;

    @XmlAttribute(name = "dimension", required = true)
    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    @XmlAttribute(name = "width", required = true)
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @XmlAttribute(name = "height", required = true)
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @XmlAttribute(name = "mainTitle")
    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    @XmlAttribute(name = "subTitle")
    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @XmlAttribute(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlAttribute (name = "imagePath")
    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    @XmlAttribute(name = "imageUrls" ,required = true)
    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrl(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    @XmlAttribute(name = "logoUrls" ,required = true)
    public String getLogoUrls() {
        return logoUrls;
    }

    public void setLogoUrls(String logoUrls) {
        this.logoUrls = logoUrls;
    }
}

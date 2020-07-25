package com.learn.spring.mybatisservice.entity;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @project mybatis-service
 * @uthor kumar
 * @since 7/25/2020
 */
@XmlRootElement(name = "IntelligentAttribute")
public class BannerXmlVO {
    private String imageUrl;
    private String imageLogo;
    private Heading headings;
    private List<DimensionVO>elements;
    private Integer width;
    private Integer height;
    private String outputPath;

    public String getImageUrl() {
        return imageUrl;
    }

    @XmlTransient
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @XmlTransient
    public String getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(String imageLogo) {
        this.imageLogo = imageLogo;
    }

    @XmlTransient
    public Heading getHeadings() {
        return headings;
    }

    public void setHeadings(Heading headings) {
        this.headings = headings;
    }

    @XmlElement(name = "element")
    public List<DimensionVO> getElements() {
        return elements;
    }

    public void setElements(List<DimensionVO> elements) {
        this.elements = elements;
    }

    @XmlAttribute(name = "width", required = true)
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @XmlAttribute(name = "height", required = false)
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @XmlAttribute(name = "outputPath", required = true)
    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

}

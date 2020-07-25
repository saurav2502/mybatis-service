package com.learn.spring.mybatisservice.entity;

/**
 * @project mybatis-service
 * @uthor kumar
 * @since 7/25/2020
 */
public class Heading {
    private String mainText;
    private String subtitle;
    private String summary;

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}

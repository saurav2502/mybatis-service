package com.learn.spring.mybatisservice.entity;

/**
 * @author kumar
 * @project mybatis-service
 * @since 8/5/2020
 */
public class IndusTypeInfo {

    private int indusTypeId;
    private String indusTypeName;

    public IndusTypeInfo() {
    }

    public IndusTypeInfo(int indusTypeId, String indusTypeName) {
        this.indusTypeId = indusTypeId;
        this.indusTypeName = indusTypeName;
    }

    public int getIndusTypeId() {
        return indusTypeId;
    }

    public void setIndusTypeId(int indusTypeId) {
        this.indusTypeId = indusTypeId;
    }

    public String getIndusTypeName() {
        return indusTypeName;
    }

    public void setIndusTypeName(String indusTypeName) {
        this.indusTypeName = indusTypeName;
    }
}

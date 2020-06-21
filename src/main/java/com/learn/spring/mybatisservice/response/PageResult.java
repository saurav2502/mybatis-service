package com.learn.spring.mybatisservice.response;

import java.util.List;

public class PageResult<T> {

    public static final long DEFAULT_OFFSET = 0;
    public static final int DEFAULT_MAX_NO_OF_ROWS = 1000;
    private long totalElements;
    private PageVO pageVO;
    private List<T> elements;
    private Class <T> clazz;

    public PageResult(List<T> elements, PageVO pageVO,Class clazz) {
        this.elements = elements;
        this.pageVO = pageVO;
        this.clazz = clazz;
    }

    public PageResult(long totalElements, List<T> elements, PageVO pageVO, Class clazz) {
        this.totalElements = totalElements;
        this.elements = elements;
        this.pageVO = pageVO;
        this.clazz = clazz;
    }

    public long getTotalElements() {
        return elements.size();
    }

    public List<T> getElements() {
        return elements;
    }

    public PageVO getPageVO() {
        return pageVO;
    }

    public void setPageVO(PageVO pageVO) {
        this.pageVO = pageVO;
    }
}

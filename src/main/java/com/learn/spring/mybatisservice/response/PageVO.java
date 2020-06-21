package com.learn.spring.mybatisservice.response;

public class PageVO<T> {
    private int pageCurr;
    private int pageSize;
    private int startPage;
    private int endpage;
    private long totalRows;
    private int totalPages;


    public PageVO() {
    }

    public PageVO(int pageCurr, int pageSize, long totalRows, int totalPages, int startPage, int endpage) {
        this.pageCurr = pageCurr;
        this.pageSize = pageSize;
        this.totalRows = totalRows;
        this.totalPages = totalPages;
        this.startPage = startPage;
        this.endpage = endpage;
    }

    public int getPageCurr() {
        return pageCurr;
    }

    public void setPageCurr(int pageCurr) {
        this.pageCurr = pageCurr;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndpage() {
        return endpage;
    }

    public void setEndpage(int endpage) {
        this.endpage = endpage;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "pageNum=" + pageCurr +
                ", pageSize=" + pageSize +
                ", startPage=" + startPage +
                ", endpage=" + endpage +
                ", totalRows=" + totalRows +
                ", totalPages=" + totalPages +
                '}';
    }
}

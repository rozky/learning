package com.rozarltd.domain;

public class Page {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private int page;
    private int pageSize;

    public Page(Integer page, Integer pageSize) {
        this.page = (page == null || page < 1) ? 1 : page;
        this.pageSize = (pageSize == null || pageSize < 0) ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return (page - 1) * pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}

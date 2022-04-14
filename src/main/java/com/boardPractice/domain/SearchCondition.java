package com.boardPractice.domain;

public class SearchCondition {
    private Integer pageSize = 10;
    private Integer page = 1;
    private String option = "";
    private String keyword = "";
    private int offset;


    public SearchCondition(Integer page, Integer pageSize){
        this.page = page;
        this.pageSize = pageSize;
        this.offset = (page - 1) * pageSize;
    }

    public SearchCondition(Integer page, Integer pageSize, String option, String keyword) {
        this(page, pageSize);
        this.option = option;
        this.keyword = keyword;
    }

    public SearchCondition(Integer page){
        this(page, 10);
    }

    SearchCondition(){}

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "pageSize=" + pageSize +
                ", page=" + page +
                ", option='" + option + '\'' +
                ", keyword='" + keyword + '\'' +
                ", offset=" + offset +
                '}';
    }
}

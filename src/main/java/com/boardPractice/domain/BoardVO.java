package com.boardPractice.domain;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.Objects;

public class BoardVO {

    // 1. 페이징 처리를 위한 변수
    private PageMaker pageMaker;

    // 2. 페이지에 대한 변수
    private Integer page;
    private Integer pageSize;
    private String action;

    // 3. 게시물에 대한 변수
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private Date regdate;
    private Integer viewcnt;


    public BoardVO(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public BoardVO(Integer page, Integer pageSize){
        this.page = page;
        this.pageSize = pageSize;
    }


    public BoardVO(){}

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Integer getViewcnt() {
        return viewcnt;
    }

    public void setViewcnt(Integer viewcnt) {
        this.viewcnt = viewcnt;
    }

    public PageMaker getPageMaker() {
        return pageMaker;
    }

    public void setPageMaker(PageMaker pageMaker) {
        this.pageMaker = pageMaker;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    // list를 가져올 때 offset 설정을 위한 메서드
    public int getOffset(){
        return (page - 1) * pageSize;
    }

    // redirect를 할때 queryString을 만드는 변수
    public String getQuery(String action){
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("action", action)
                .queryParam("bno", bno).build().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardVO boardVO = (BoardVO) o;
        return Objects.equals(bno, boardVO.bno) && Objects.equals(title, boardVO.title) && Objects.equals(content, boardVO.content) && Objects.equals(writer, boardVO.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bno, title, content, writer);
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "pageMaker=" + pageMaker +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", action='" + action + '\'' +
                ", bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regdate=" + regdate +
                ", viewcnt=" + viewcnt +
                '}';
    }
}
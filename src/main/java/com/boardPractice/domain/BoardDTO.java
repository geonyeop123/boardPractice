package com.boardPractice.domain;

import java.util.Date;
import java.util.Objects;

public class BoardDTO {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private Date regdate;
    private Integer viewcnt;

    public BoardDTO(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public BoardDTO(){}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDTO boardDTO = (BoardDTO) o;
        return bno.equals(boardDTO.bno) && title.equals(boardDTO.title) && content.equals(boardDTO.content) && writer.equals(boardDTO.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bno, title, content, writer);
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regdate=" + regdate +
                ", viewcnt=" + viewcnt +
                '}';
    }
}

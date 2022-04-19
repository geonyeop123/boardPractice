package com.boardPractice.domain;

import java.util.Date;
import java.util.Objects;

public class BoardDTO {

    private Integer bno;
    private Integer ref;
    private Integer step;
    private Integer depth;
    private String title;
    private String content;
    private String writer;
    private Date regdate;
    private String blindYn;
    private String replyTag ="";

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
        for(int i = 0; i < step;i++){
            replyTag+="&nbsp;&nbsp;&nbsp;";
        }
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
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

    public String getBlindYn() {
        return blindYn;
    }

    public void setBlindYn(String blindYn) {
        this.blindYn = blindYn;
    }

    public String getReplyTag() {
        return replyTag;
    }

    public void setReplyTag(String replyTag) {
        this.replyTag = replyTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDTO boardDTO = (BoardDTO) o;
        return Objects.equals(bno, boardDTO.bno) && Objects.equals(ref, boardDTO.ref) && Objects.equals(step, boardDTO.step) && Objects.equals(title, boardDTO.title) && Objects.equals(content, boardDTO.content) && Objects.equals(writer, boardDTO.writer) && Objects.equals(blindYn, boardDTO.blindYn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bno, ref, step, title, content, writer, blindYn);
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "bno=" + bno +
                ", ref=" + ref +
                ", step=" + step +
                ", depth=" + depth +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regdate=" + regdate +
                ", blindYn='" + blindYn + '\'' +
                ", replyTag='" + replyTag + '\'' +
                '}';
    }
}

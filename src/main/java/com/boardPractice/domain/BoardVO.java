package com.boardPractice.domain;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class BoardVO {

    // 1. 게시물 정보를 위한 변수

    List<BoardDTO> list;

    BoardDTO boardDTO;

    // 2. 페이징 처리를 위한 변수
    private PageMaker pageMaker;

    // 3. 페이지에 대한 변수
    private Integer page;
    private Integer pageSize;

    // 4. 로직 처리를 위한 변수
    private String action;

    private Integer parentBno;

    private StringBuffer msg;


    public BoardVO(Integer page, Integer pageSize){
        this.page = page;
        this.pageSize = pageSize;
    }
    public BoardVO(){
        boardDTO = new BoardDTO();
    }

    public List<BoardDTO> getList() {
        return list;
    }

    public void setList(List<BoardDTO> list) {
        this.list = list;
    }

    public BoardDTO getBoardDTO() {
        return boardDTO;
    }

    public void setBoardDTO(BoardDTO boardDTO) {
        this.boardDTO = boardDTO;
    }

    public void setMsg(StringBuffer msg) {
        this.msg = msg;
    }

    public String getTitle(){
        return this.boardDTO.getTitle();
    }

    public void setTitle(String title){
        this.boardDTO.setTitle(title);
    }

    public String getContent(){
        return this.boardDTO.getContent();
    }

    public void setContent(String content){
        this.boardDTO.setContent(content);
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

    public Integer getParentBno() {
        return parentBno;
    }

    public void setParentBno(Integer parentBno) {
        this.parentBno = parentBno;
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

    public Integer getBno() {
        return boardDTO.getBno();
    }

    public void setBno(Integer bno) {
        this.boardDTO.setBno(bno);
    }

    public String getMsg() {
        if(this.msg == null) this.msg = new StringBuffer();
        return msg.toString();
    }

    public void setMsg(String msg) {
        if(this.msg == null) this.msg = new StringBuffer();
        this.msg.append(msg);
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
                .queryParam("bno", list.get(0).getBno()).build().toString();
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "list=" + list +
                ", boardDTO=" + boardDTO +
                ", pageMaker=" + pageMaker +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", action='" + action + '\'' +
                ", parentBno=" + parentBno +
                ", msg=" + msg +
                '}';
    }
}
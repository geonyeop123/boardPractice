package com.boardPractice.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class PageMaker {
    private SearchCondition sc;
    private int totalCnt;
    private int naviSize = 10;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;

    PageMaker(){}


    public PageMaker(SearchCondition sc, int totalCnt){
        this.sc = sc;
        this.totalCnt = totalCnt;
        startPage = (sc.getPage() - 1) / 10 * 10 + 1;
        endPage = Math.min(startPage + naviSize - 1, (int)Math.ceil(totalCnt / (double)naviSize));
        prev = startPage != 1 ? true  : false;
        next = endPage < (int)Math.ceil(totalCnt / (double)naviSize) ? true : false;
    }

    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public void showPage(){
        System.out.println("page : " + sc.getPage());
        System.out.print(prev ? "[PREV]" : "");
        for(int i = startPage; i <= endPage; i++){
            System.out.print(i + " ");
        }
        System.out.println(next ? "[NEXT]" : "");
    }

    @Override
    public String toString() {
        return "PageMaker{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", naviSize=" + naviSize +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", prev=" + prev +
                ", next=" + next +
                '}';
    }
}

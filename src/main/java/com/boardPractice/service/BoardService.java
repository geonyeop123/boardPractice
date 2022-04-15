package com.boardPractice.service;


import com.boardPractice.domain.BoardVO;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.domain.SearchCondition;

import java.util.List;

public interface BoardService {
    String write(BoardVO boardVO) throws Exception;

    BoardVO select(Integer bno) throws Exception;

    String modify(BoardVO boardVO) throws Exception;

    String delete(Integer bno) throws Exception;

    List<BoardVO> listAll(SearchCondition sc) throws Exception;

    List<BoardVO> conditionList(PageMaker pm) throws Exception;

    int totalCnt() throws Exception;
}

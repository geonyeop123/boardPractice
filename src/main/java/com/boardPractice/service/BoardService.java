package com.boardPractice.service;

import com.boardPractice.domain.BoardDTO;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int write(BoardDTO boardDTO) throws Exception;

    BoardDTO select(Integer bno) throws Exception;

    int modify(BoardDTO boardDTO) throws Exception;

    int delete(Integer bno) throws Exception;

    List<BoardDTO> listAll(SearchCondition sc) throws Exception;

    List<BoardDTO> conditionList(PageMaker pm) throws Exception;

    int totalCnt() throws Exception;
}

package com.boardPractice.service;


import com.boardPractice.domain.BoardVO;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.domain.SearchCondition;

import java.util.List;

public interface BoardService {

    List<BoardVO> list(BoardVO boardVO) throws Exception;

    BoardVO write(BoardVO boardVO) throws Exception;

    String proc(BoardVO boardVO) throws Exception;

}

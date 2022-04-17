package com.boardPractice.persistence;


import com.boardPractice.domain.BoardVO;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.domain.SearchCondition;

import java.util.List;

public interface BoardDAO {
    int createParent(BoardVO boardVO) throws Exception;

    int createChild(BoardVO boardVO) throws Exception;

    BoardVO read(Integer bno) throws Exception;

    int update(BoardVO boardVO) throws Exception;

    int updateDepth(BoardVO boardVO) throws Exception;

    int delete(Integer bno) throws Exception;

    int deleteAll() throws Exception;

    List<BoardVO> listAll(BoardVO boardVO) throws Exception;

    int totalCnt() throws Exception;
}

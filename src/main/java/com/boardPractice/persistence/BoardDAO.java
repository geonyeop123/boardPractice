package com.boardPractice.persistence;

import com.boardPractice.domain.BoardDTO;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
    int create(BoardDTO boardDTO) throws Exception;

    BoardDTO read(Integer bno) throws Exception;

    int update(BoardDTO boardDTO) throws Exception;

    int delete(Integer bno) throws Exception;

    int deleteAll() throws Exception;

    List<BoardDTO> listAll(PageMaker pm) throws Exception;

    List<BoardDTO> conditionList(PageMaker pm) throws Exception;

    int conditionListCnt(SearchCondition sc) throws Exception;

    int totalCnt() throws Exception;
}

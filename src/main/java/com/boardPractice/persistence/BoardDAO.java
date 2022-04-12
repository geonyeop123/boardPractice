package com.boardPractice.persistence;

import com.boardPractice.domain.BoardDTO;
import com.boardPractice.domain.PageMaker;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
    int create(BoardDTO boardDTO) throws Exception;

    BoardDTO read(Integer bno) throws Exception;

    int update(BoardDTO boardDTO) throws Exception;

    int delete(Integer bno) throws Exception;

    int deleteAll() throws Exception;

    List<BoardDTO> listAll(PageMaker pm) throws Exception;

    int totalCnt() throws Exception;
}

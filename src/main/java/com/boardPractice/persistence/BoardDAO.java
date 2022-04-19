package com.boardPractice.persistence;


import com.boardPractice.domain.BoardDTO;
import com.boardPractice.domain.BoardVO;

import java.util.List;

public interface BoardDAO {

    int deleteCheck(BoardDTO boarDTO) throws Exception;

    int create(BoardVO boardVO) throws Exception;

    BoardDTO read(Integer bno) throws Exception;

    int update(BoardDTO boardDTO) throws Exception;

    int createUpdateDepth(BoardDTO boardDTO) throws Exception;

    int deleteUpdateDepth(BoardDTO boardDTO) throws Exception;

    int delete(Integer bno) throws Exception;

    int deleteAll() throws Exception;

    List<BoardDTO> listAll(BoardVO boardVO) throws Exception;

    int totalCnt() throws Exception;
}

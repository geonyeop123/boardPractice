package com.boardPractice.service;


import com.boardPractice.domain.BoardVO;

public interface BoardService {

    BoardVO list(BoardVO boardVO) throws Exception;

    BoardVO write(BoardVO boardVO) throws Exception;

    BoardVO proc(BoardVO boardVO) throws Exception;

}

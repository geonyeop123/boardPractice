package com.boardPractice.service;

import com.boardPractice.domain.BoardDTO;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.domain.SearchCondition;
import com.boardPractice.persistence.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDAO boardDAO;

    @Override
    public int write(BoardDTO boardDTO) throws Exception{
        return boardDAO.create(boardDTO);
    }

    @Override
    public BoardDTO select(Integer bno) throws Exception{
        return boardDAO.read(bno);
    }

    @Override
    public int modify(BoardDTO boardDTO) throws Exception{
        return boardDAO.update(boardDTO);
    }

    @Override
    public int delete(Integer bno) throws Exception{
        return boardDAO.delete(bno);
    }

    @Override
    public List<BoardDTO> listAll(SearchCondition sc) throws Exception{
        return boardDAO.listAll(sc);
    }

    @Override
    public List<BoardDTO> conditionList(PageMaker pm) throws Exception {
        return boardDAO.conditionList(pm);
    }

    @Override
    public int totalCnt() throws Exception{
        return boardDAO.totalCnt();
    }
}

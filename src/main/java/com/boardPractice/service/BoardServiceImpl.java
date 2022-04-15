package com.boardPractice.service;

import com.boardPractice.domain.BoardVO;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.domain.SearchCondition;
import com.boardPractice.persistence.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDAO boardDAO;

    @Override
    public String write(BoardVO boardVO) throws Exception{
        return 1 == boardDAO.create(boardVO) ? "WRT_OK" : "WRT_ERR";
    }

    @Override
    public BoardVO select(Integer bno) throws Exception{
        return boardDAO.read(bno);
    }

    @Override
    public String modify(BoardVO boardVO) throws Exception{
        return 1 == boardDAO.update(boardVO) ? "MOD_OK" : "MOD_ERR";
    }

    @Override
    public String delete(Integer bno) throws Exception{
        return 1 == boardDAO.delete(bno) ? "DEL_OK" : "DEL_ERR";
    }

    @Override
    public List<BoardVO> listAll(SearchCondition sc) throws Exception{
        return boardDAO.listAll(sc);
    }

    @Override
    public List<BoardVO> conditionList(PageMaker pm) throws Exception {
        return boardDAO.conditionList(pm);
    }

    @Override
    public int totalCnt() throws Exception{
        return boardDAO.totalCnt();
    }
}

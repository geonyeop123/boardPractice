package com.boardPractice.persistence;

import com.boardPractice.domain.BoardDTO;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.domain.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    SqlSession sqlSession;

    private static final String namespace = "com.boardPractice.mapper.BoardMapper.";

    @Override
    public int create(BoardDTO boardDTO) throws Exception{
        return sqlSession.insert(namespace + "create", boardDTO);
    }

    @Override
    public BoardDTO read(Integer bno) throws Exception{
        return sqlSession.selectOne(namespace + "select", bno);
    }

    @Override
    public int update(BoardDTO boardDTO) throws Exception{
        return sqlSession.update(namespace + "update", boardDTO);
    }

    @Override
    public int delete(Integer bno) throws Exception{
        return sqlSession.delete(namespace + "delete", bno);
    }

    @Override
    public int deleteAll() throws Exception{
        return sqlSession.delete(namespace + "deleteAll");
    }

    @Override
    public List<BoardDTO> listAll(PageMaker pm) throws Exception{
        return sqlSession.selectList(namespace + "listAll", pm);
    }

    @Override
    public List<BoardDTO> conditionList(PageMaker pm) throws Exception {
        return sqlSession.selectList(namespace + "conditionList", pm);
    }

    @Override
    public int totalCnt() throws Exception{
        return sqlSession.selectOne(namespace + "totalCnt");
    }

    @Override
    public int conditionListCnt(SearchCondition sc) throws Exception {
        return sqlSession.selectOne(namespace + "conditionListCnt", sc);
    }
}

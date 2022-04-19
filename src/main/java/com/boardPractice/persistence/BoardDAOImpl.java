package com.boardPractice.persistence;

import com.boardPractice.domain.BoardDTO;
import com.boardPractice.domain.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    SqlSession sqlSession;

    private static final String namespace = "com.boardPractice.mapper.BoardMapper.";

    @Override
    public int create(BoardVO boardVO) throws Exception {
        return sqlSession.insert(namespace + "create", boardVO);
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
    public int createUpdateDepth(BoardDTO boardDTO) throws Exception {
        return sqlSession.update(namespace + "createUpdateDepth", boardDTO);
    }

    @Override
    public int deleteUpdateDepth(BoardDTO boardDTO) throws Exception {
        return sqlSession.update(namespace + "deleteUpdateDepth", boardDTO);
    }


    @Override
    public int deleteCheck(BoardDTO boardDTO) throws Exception {
        return sqlSession.selectOne(namespace + "deleteCheck", boardDTO);
    }

    @Override
    public int delete(Integer bno) throws Exception{
        return sqlSession.update(namespace + "delete", bno);
    }

    @Override
    public int deleteAll() throws Exception{
        return sqlSession.delete(namespace + "deleteAll");
    }

    @Override
    public List<BoardDTO> listAll(BoardVO boardVO) throws Exception{
        return sqlSession.selectList(namespace + "listAll", boardVO);
    }

    @Override
    public int totalCnt() throws Exception{
        return sqlSession.selectOne(namespace + "totalCnt");
    }

}

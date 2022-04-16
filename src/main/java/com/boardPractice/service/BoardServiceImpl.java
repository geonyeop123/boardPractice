package com.boardPractice.service;

import com.boardPractice.domain.BoardVO;
import com.boardPractice.domain.NoBoardException;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.persistence.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDAO boardDAO;

    @Override
    public List<BoardVO> list(BoardVO boardVO) throws Exception {

        // #####
        // # 변수 선언
        // #####
        int totalCnt = 0;
        List<BoardVO> list = null;
        PageMaker pm = null;

        // #####
        // # 처리 로직
        // #####

        // 1. 페이징을 위한 처리
        totalCnt = boardDAO.totalCnt();
        pm = new PageMaker(boardVO.getPage(), boardVO.getPageSize(), totalCnt);

        // 2. 게시판에 보여줄 List 얻어오기
        list = boardDAO.listAll(boardVO);

        // 3. 페이징 처리를 위해 첫번째 boardVO에 pageMaker 값 담기
        list.get(0).setPageMaker(pm);

        // 4. List 반환
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BoardVO write(BoardVO boardVO) throws Exception{
        // #####
        // # 변수 선언
        // #####
        BoardVO vo = null;

        // #####
        // # 처리 로직
        // #####
        // 1. MOD일 시 title과 content를 표시해주기
        if("MOD".equals(boardVO.getAction())){

            // 2. 게시물 정보 가져오기
            vo = boardDAO.read(boardVO.getBno());

            // 3. 게시물이 없다면 NoBoardException 발생
            if(vo == null) throw new NoBoardException("WRT_ERR");

            // 4. 게시물이 있다면, title, content 값 넣어주기
            boardVO.setTitle(vo.getTitle());
            boardVO.setContent(vo.getContent());

        }

        return boardVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String proc(BoardVO boardVO) throws Exception {

        // 메시지의 형태는 action_OK action_ERR 이므로, 미리 action 값으로 초기화
        String message = boardVO.getAction();
        int resultCnt = 0;

        // 1. 로그인 작업이 없기 때문에 writer 하드코딩
        boardVO.setWriter("yeop");

        // 2. action에 따라 처리
            // 2-1 WRT(쓰기)인 경우
            if("WRT".equals(boardVO.getAction())){
                // 2-1-1 create 실행
                resultCnt = boardDAO.create(boardVO);

            // 2-2 아닌 경우
            }else {
                // 2-2-1. 게시물이 존재하는지 확인 없으면 NoBoardException 발생
                if(boardDAO.read(boardVO.getBno()) == null) throw new NoBoardException("MOD_ERR");
            }
            // 2-3 MOD(수정)인 경우
            if("MOD".equals(boardVO.getAction())){

                // 2-3-1. update 실행
                resultCnt = boardDAO.update(boardVO);

            // 2-4 DEL(삭제)인 경우
            }else if("DEL".equals(boardVO.getAction())){

                // 2-4-1. 존재하는 경우 delete 실행
                resultCnt = boardDAO.delete(boardVO.getBno());
            }

        message = resultCnt == 1 ? message + "_OK" : message + "_ERR";

        return message;
    }
}

package com.boardPractice.service;

import com.boardPractice.domain.BoardDTO;
import com.boardPractice.domain.BoardVO;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.persistence.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDAO boardDAO;

    @Override
    public BoardVO list(BoardVO boardVO) throws Exception {

        // #####
        // # 변수 선언
        // #####
        PageMaker pm = null;

        // #####
        // # 처리 로직
        // #####

        // 1. 페이징을 위한 처리
        pm = new PageMaker(boardVO.getPage(), boardVO.getPageSize(), boardDAO.totalCnt());

        // 1-1. 페이징 정보 VO에 담기
        boardVO.setPageMaker(pm);

        // 2. 게시판에 보여줄 List 얻어와 VO에 담기
        boardVO.setList(boardDAO.listAll(boardVO));

        // 4. List 반환
        return boardVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BoardVO write(BoardVO boardVO) throws Exception{
        // #####
        // # 변수 선언
        // #####
        BoardDTO dto = null;

        // #####
        // # 처리 로직
        // #####

        // 1. MOD일 시 DTO 담아주기
        if("MOD".equals(boardVO.getAction())){
            // 2. 게시물 정보 가져오기
            dto = boardDAO.read(boardVO.getBno());
            // 3. 게시물이 없다면 msg에 에러 담기
            if(dto == null) boardVO.setMsg("ERR_NoBoard");
            // 4. 게시물이 있다면, vo에 담기
            boardVO.setBoardDTO(dto);
        }

        return boardVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BoardVO proc(BoardVO boardVO) throws Exception {

        // #####
        // # 변수 선언
        // #####

        // 메시지의 형태는 action_OK action_ERR 이므로, 미리 action 값으로 초기화
        String message = boardVO.getAction();
        BoardVO refVO = null;
        int resultCnt = 0;

        // #####
        // # 처리 로직
        // #####

        // 1. 로그인 작업이 없기 때문에 writer 하드코딩
        boardVO.getBoardDTO().setWriter("yeop");

        // 2. action에 따라 처리
            // 2-1 WRT(쓰기)인 경우
            if ("WRT".equals(boardVO.getAction())) {
                // create 실행
                resultCnt = boardDAO.create(boardVO);

            // 2-2 아닌 경우
            } else {
                // 2-2-1. 게시물이 존재하는지 확인 없으면 msg에 담기
                if (boardDAO.read(boardVO.getBno()) == null) {
                    message = "ERR_NoBoard";
                }else{
                    // 2-3 MOD(수정)인 경우
                    if ("MOD".equals(boardVO.getAction())) {

                        // 2-3-1. update 실행
                        resultCnt = boardDAO.update(boardVO.getBoardDTO());

                        // 2-4 DEL(삭제)인 경우
                    } else if ("DEL".equals(boardVO.getAction())) {

                        // 2-4-1. 존재하는 경우 delete 실행 이때, 답글도 모두 삭제된다.
                        resultCnt = boardDAO.delete(boardVO.getBno());
                        System.out.println(resultCnt);
                    } else if ("REP".equals(boardVO.getAction())){
                        resultCnt = boardDAO.create(boardVO);
                    }
                    message = (resultCnt > 0) ? message + "_OK" : message + "_ERR";
                }
            }

        boardVO.setMsg(message);
        System.out.println(boardVO.getMsg());
        return boardVO;
    }
}

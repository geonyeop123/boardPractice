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
        int totalCnt = 0;

        // #####
        // # 처리 로직
        // #####

        // 1. 페이징을 위한 처리

        // 1-1. 게시물 총 개수를 가져온다.
        totalCnt = boardDAO.totalCnt();

        // 1-2. 게시물 총 개수가 0이 아닐 시
        if(totalCnt != 0) {
            pm = new PageMaker(boardVO.getPage(), boardVO.getPageSize(), totalCnt);

            // 1-1. 페이징 정보 VO에 담기
            boardVO.setPageMaker(pm);

            // 2. 게시판에 보여줄 List 얻어와 VO에 담기
            boardVO.setList(boardDAO.listAll(boardVO));
        }

        // 4. List를 담은 VO 반환
        return boardVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BoardVO write(BoardVO boardVO) throws Exception{

        // #####
        // # 변수 선언
        // #####
        BoardDTO dto = null;
        String action = null;

        // #####
        // # 처리 로직
        // #####

        // 1. action 값 가져오기
        action = boardVO.getAction();
        // 2. action 유효성 검사
        if("MOD".equals(action)){
                // 4. 게시물 정보 가져오기
                dto = boardDAO.read(boardVO.getBno());
                // 5. 게시물이 없다면 msg에 에러 담기
                if(dto == null) boardVO.setMsg("ERR_NoBoard");
                // 6. 현재 VO에 title이 없으면 vo에 dto 담기
                // proc에서 error발생 시 작성하고 있던 title, content를 다시 주입하기 위해 DTO를 주입하지 않음
                if(boardVO.getTitle() == null || boardVO.getContent() == null) boardVO.setBoardDTO(dto);
        }else if(!"REP".equals(action)){
            boardVO.setAction("WRT");
        }

        return boardVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BoardVO proc(BoardVO boardVO) throws Exception {

        // #####
        // # 변수 선언
        // #####

        // 메시지를 담기 위한 변수
        StringBuffer message = new StringBuffer();
        // 현재 게시물 정보를 담기 위한 변수
        BoardDTO currentDTO = null;
        // 기본 err와 다른 에러일 시 표시하기 위한 변수
        boolean diffent_err = false;
        // DB result row를 담기 위한 변수
        int resultCnt = 0;

        // #####
        // # 처리 로직
        // #####

        // 1. 로그인 작업이 없기 때문에 writer 하드코딩
        boardVO.getBoardDTO().setWriter("yeop");




        // write가 아니면 게시물 조회
        if(!"WRT".equals(boardVO.getAction())){
            // 2-2-1. 현재 게시물을 currentDTO에 담기
            currentDTO = boardDAO.read(boardVO.getBno());
            // 2-2-2. 만일, 현재 게시물이 없다면, error 메시지 담기
            if (currentDTO == null) {
                message.append("ERR_NoBoard");
                diffent_err = true;
            }
        }
        // 2. action에 따라 처리

        if ("MOD".equals(boardVO.getAction())) {

            // 2-3-1. update 실행
            resultCnt = boardDAO.update(boardVO.getBoardDTO());

            // 2-4 DEL(삭제)인 경우
        } else if ("DEL".equals(boardVO.getAction())) {

            // 2-4-1. 삭제할 수 있는지 확인(답글이 있는지 확인)
            resultCnt = boardDAO.deleteCheck(currentDTO);

            // 2-4-2. 답글이 있다면 error 메시지 담기
            if(resultCnt > 0){
                message.append("ERR_HaveRep");
                diffent_err = true;
                // 2-4-3. 없다면 삭제 진행
            }else{

                // 2-4-5. 현재 게시물보다 높은 depth를 가진 게시물들의 depth를 -1씩 해주기
                boardDAO.deleteUpdateDepth(currentDTO);

                // 2-4-6. 게시물 삭제
                resultCnt = boardDAO.delete(boardVO.getBno());
            }
        }else {
            if ("REP".equals(boardVO.getAction())) {
                // 2-5-2. 현재 게시물의 ref정보를 입력
                boardVO.setRef(currentDTO.getRef());
                // 2-5-3. 현재 게시물의 step에서 1을 증가한 값을 입력
                boardVO.setStep(currentDTO.getStep() + 1);
                // 2-5-4. 현재 게시물의 depth에서 1을 증가한 값을 입력
                boardVO.setDepth(currentDTO.getDepth() + 1);
                // 2-5-5. 답글로 등록될 depth보다 높거나 같은 게시물의 depth를 1씩 증가
                boardDAO.createUpdateDepth(boardVO.getBoardDTO());
            }
            resultCnt = boardDAO.create(boardVO);
        }

        // 일반 에러 결과에 대한 메시지 담기
        if(!diffent_err){
            if(resultCnt > 0) message.append("SUC_" + boardVO.getAction());
                else
                    message.append("ERR_" + boardVO.getAction());
        }

        boardVO.setMsg(message.toString());

        return boardVO;
    }
}

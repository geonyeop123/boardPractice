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
//    public BoardVO list(BoardVO boardVO) throws Exception {
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

        // 1-1. 게시물이 없는 경우 null return
        if(totalCnt == 0) return null;

        // 1-2. 게시물이 있는 경우 pm 생성
        pm = new PageMaker(boardVO.getPage(), boardVO.getPageSize(), totalCnt);

        // 2. 게시판에 보여줄 List 얻어오기
//        boardVO.setList(list);
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

            // 4. 게시물이 있다면, title, content, ref 값 넣어주기
            boardVO.setTitle(vo.getTitle());
            boardVO.setContent(vo.getContent());
            boardVO.setRef(vo.getRef());

            // VO안에 dto에 넣기
        }
        // WRT action은 필요 없음

        return boardVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String proc(BoardVO boardVO) throws Exception {

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
        boardVO.setWriter("yeop");

        // 2. action에 따라 처리
            // 2-1 WRT(쓰기)인 경우
            if ("WRT".equals(boardVO.getAction())) {
                // 2-1-1 Comment 게시물이 아닌 경우 createParent 실행
                if (!boardVO.isReply()) {
                    resultCnt = boardDAO.createParent(boardVO);

                }else {
                    // 2-1-2 Comment 게시물인 경우 답글을 달려는 글을 가져온다.
                    refVO = boardDAO.read(boardVO.getParentBno());

                    // 2-1-3 해당 refVO가 없을 시 NoBoardException 발생
                    if (refVO == null) throw new NoBoardException("WRT_ERR");

                    // 2-1-3 있으면 boardVO에 ref, step, depth 지정
                    boardVO.setRef(refVO.getRef());
                    boardVO.setStep(refVO.getStep() + 1);
                    boardVO.setDepth(refVO.getDepth() + 1);

                    // 2-1-4 해당 ref 게시물 중 지정된 depth 보다 같거나 큰 depth가 있는 경우 1씩 증가
                    boardDAO.updateDepth(boardVO);

                    // 2-1-5 createChild 실행
                    resultCnt = boardDAO.createChild(boardVO);
                }
            // 2-2 아닌 경우
            } else {
                // 2-2-1. 게시물이 존재하는지 확인 없으면 NoBoardException 발생
                if (boardDAO.read(boardVO.getBno()) == null) throw new NoBoardException("MOD_ERR");
            }
            // 2-3 MOD(수정)인 경우
            if ("MOD".equals(boardVO.getAction())) {

                // 2-3-1. update 실행
                resultCnt = boardDAO.update(boardVO);

                // 2-4 DEL(삭제)인 경우
            } else if ("DEL".equals(boardVO.getAction())) {

                // 2-4-1. 존재하는 경우 delete 실행 이때, 답글도 모두 삭제된다.
                resultCnt = boardDAO.delete(boardVO.getBno());
                System.out.println(resultCnt);
            }

        message = (resultCnt > 0) ? message + "_OK" : message + "_ERR";

        return message;
    }
}

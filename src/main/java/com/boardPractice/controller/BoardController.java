package com.boardPractice.controller;

import com.boardPractice.domain.BoardVO;
import com.boardPractice.domain.NoBoardException;
import com.boardPractice.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger =
            LoggerFactory.getLogger(BoardController.class);

    @Autowired
    BoardService service;

    @GetMapping("/list")
    public String list(BoardVO boardVO, Model m){

        // #####
        // # 변수 선언
        // #####
        List<BoardVO> list = null;

        // #####
        // # 처리 로직
        // #####

        // 1. page 및 pageSize값이 없을 경우 초기 세팅
        if(boardVO.getPage() == null || boardVO.getPageSize() == null){
            boardVO = new BoardVO(1, 10);
        }
        // new 하지 말고 set으로 하는게 더 좋음

       try{

       // 2. list 및 pageMaker 받아오기
           list = service.list(boardVO);

       }catch(Exception e){ e.printStackTrace(); }

       // throw 던지기


        // #####
        // # 반환
        // #####

        // list가 null이 아니면 Model에 담아주기
        if(list != null) m.addAttribute(list);
        // name 지정하면 model null 에러가 발생하지 않을 것임



       return "list";
    }


    @GetMapping("/write")
    public String write(BoardVO boardVO , Model m){
        // #####
        // # 변수 선언
        // #####
        String message = null;

        // #####
        // # 처리 로직
        // #####
        try{
            // 1. action에 따른 boardVO 값 가져오기
            boardVO = service.write(boardVO);

        }catch(NoBoardException ne){
            // 2. 게시물이 없다면 메시지에 해당 코드를 담는다.
            message = "ERR_NOBOARD";

            // 3. model에 해당 메시지를 담고, proc에서 처리
            m.addAttribute("message", message);
            return "proc";
        }
        catch(Exception e){ e.printStackTrace(); }

        // #####
        // # 반환
        // #####
        m.addAttribute(boardVO);

        return "board";
    }

    @PostMapping("/proc")
    public String proc(BoardVO boardVO, Model m){
        // #####
        // # 변수 선언
        // #####
        String message = null;

        // #####
        // # 처리 로직
        // #####
        try{

            // 1. action에 따른 message 가져오기
            message = service.proc(boardVO);

        }catch(NoBoardException ne){
            // 2. 게시물이 없다면 에러코드 변경
            message = "ERR_NOBOARD";
        } catch(Exception e){ e.printStackTrace(); }

        // #####
        // # 반환
        // #####
        m.addAttribute("message", message);
        m.addAttribute(boardVO);

        return "proc";
    }

}

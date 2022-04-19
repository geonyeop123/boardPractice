package com.boardPractice.controller;

import com.boardPractice.domain.BoardVO;
import com.boardPractice.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger =
            LoggerFactory.getLogger(BoardController.class);

    @Autowired
    BoardService service;

    @GetMapping("/list")
    public String list(BoardVO boardVO, Model m)throws Exception{

        // #####
        // # 처리 로직
        // #####

        // 1. 데이터 검증
        if(boardVO == null) boardVO = new BoardVO();

        if(boardVO.getPage() == null || boardVO.getPageSize() == null){
            boardVO.setPage(1);
            boardVO.setPageSize(10);
        }

       // 2. list 및 pageMaker 받아오기
           boardVO = service.list(boardVO);

        // #####
        // # 반환
        // #####

        // boardVO 모델에 담아주기
        m.addAttribute(boardVO);

       return "list";
    }


    @GetMapping("/write")
    public String write(BoardVO boardVO , Model m)throws Exception{

        // #####
        // # 처리 로직
        // #####

            // 1. action에 따른 boardVO 값 가져오기
            boardVO = service.write(boardVO);

        // #####
        // # 반환
        // #####
        m.addAttribute(boardVO);

        return ("ERR_NoBoard".equals(boardVO.getMsg()))
                ? "proc"
                : "board";
    }

    @PostMapping("/proc")
    public String proc(BoardVO boardVO, Model m)throws Exception{
        System.out.println(boardVO);

        // #####
        // # 처리 로직
        // #####
        boardVO = service.proc(boardVO);

        // #####
        // # 반환
        // #####
        m.addAttribute(boardVO);

        return "proc";
    }

}

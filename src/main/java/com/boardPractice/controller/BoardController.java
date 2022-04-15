package com.boardPractice.controller;

import com.boardPractice.domain.BoardVO;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.domain.SearchCondition;
import com.boardPractice.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger =
            LoggerFactory.getLogger(BoardController.class);

    @Autowired
    BoardService service;

    @GetMapping("/list")
    public String list(SearchCondition sc, Model m){
        if(sc.getPage() == null || sc.getPageSize() == null) sc = new SearchCondition(1, 10);

       try{

           int totalCnt = service.totalCnt();

           PageMaker pm = new PageMaker(sc, totalCnt);

           List<BoardVO> list = service.listAll(sc);

           m.addAttribute(list);
           m.addAttribute(pm);
       }catch(Exception e){
           e.printStackTrace();
       }
       return "list";
    }

    private BoardVO combineData(BoardVO pageVO, BoardVO boardVO){

        boardVO.setPage(pageVO.getPage());
        boardVO.setPageSize(pageVO.getPageSize());
        return boardVO;
    }

    @GetMapping("/write")
    public String write(BoardVO boardVO, String action , Model m){

        // action이 MOD인 경우 boardVO값을 DB에서 가져와 model에 같이 담아준다.
        if(action.equals("MOD")){
            try{
                // QueryString으로 받은 page정보와 service를 통해 받은 board정보를 합쳐준다.
                boardVO = combineData(boardVO, service.select(boardVO.getBno()));
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        // page와 board정보를 넘겨준다.
        m.addAttribute(boardVO);

        // 받은 action을 다시 넘겨준다.
        m.addAttribute("action", action);

        return "board";
    }

    @PostMapping("/proc")
    public String proc(BoardVO boardVO, String action, Model m, RedirectAttributes rttr){

        // #####
        // # 변수 선언
        // #####

        String message = null;
        String url = null;


        try{

            // #####
            // # 처리 로직
            // #####

            // 1. 로그인 작업이 없기 때문에 writer 하드코딩
            boardVO.setWriter("yeop");

            // 2. action에 따라 처리

            // 2-1. action이 wrt인 경우
            if(action.equals("WRT")){
                message = service.write(boardVO);
                url = "redirect:/board/list";

            }else if(action.equals("MOD")){

                message = service.modify(boardVO);
                url = "redirect:/board/write" + boardVO.getQuery(action);

            }else if(action.equals("DEL")){

                message = service.delete(boardVO.getBno());
                url = "redirect:/board/list" + boardVO.getQuery(action);

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        rttr.addFlashAttribute("message", message);

        m.addAttribute(boardVO);
        return url;
    }


//    @PostMapping("/write")
//    public String write(BoardVO boardVO, RedirectAttributes rttr){
//        try{
//            boardVO.setWriter("yeop");
//
//
//            rttr.addFlashAttribute("message", "WRT_OK");
//        }catch(Exception e){
//            e.printStackTrace();
//            rttr.addFlashAttribute("message", "WRT_ERROR");
//        }
//        return "redirect:/board/list";
//    }
//    @PostMapping("/modify")
//    public String modify(BoardVO boardVO,Integer page, Integer pageSize,
//                         RedirectAttributes rttr, Model m){
//        try{
//            boardVO.setWriter("yeop");
//            int resultCnt = service.modify(boardVO);
//            if(resultCnt != 1) throw new Exception();
//            String uri = UriComponentsBuilder.newInstance()
//                    .queryParam("page", page)
//                    .queryParam("pageSize", pageSize)
//                    .queryParam("bno", boardVO.getBno()).build().toString();
//            rttr.addFlashAttribute("setting", "MOD");
//            rttr.addFlashAttribute("message", "MOD_OK");
//            return "redirect:/board/board" + uri;
//        }catch(Exception e){
//            System.out.println("error");
//            rttr.addFlashAttribute("message", "MOD_ERR");
//            m.addAttribute(boardVO);
//            m.addAttribute("page", page);
//            m.addAttribute("pageSize", pageSize);
//            m.addAttribute("setting", "MOD");
//            e.printStackTrace();
//            return "redirect:/board/modify";
//        }
//    }
//
//    @PostMapping("/remove")
//    public String remove(Integer bno, Integer page, Integer pageSize, RedirectAttributes rttr, Model m){
//        try{
//            System.out.println(bno);
//            int resultCnt = service.delete(bno);
//            if(resultCnt != 1) throw new Exception();
//
//            rttr.addFlashAttribute("message", "DEL_OK");
//            m.addAttribute("page", page);
//            m.addAttribute("pageSize", pageSize);
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return "redirect:/board/list?page=" + page + "&pageSize=" + pageSize;
//    }
//
//    @GetMapping("/board")
//    public String detail(Integer page, Integer pageSize, Integer bno, Model m){
//
//        try{
//            BoardVO boardVO = service.select(bno);
//            m.addAttribute(boardVO);
//            m.addAttribute("page", page);
//            m.addAttribute("pageSize", pageSize);
//            m.addAttribute("setting", "RED");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return "board";
//    }

}

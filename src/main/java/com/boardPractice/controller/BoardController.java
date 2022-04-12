package com.boardPractice.controller;

import com.boardPractice.domain.BoardDTO;
import com.boardPractice.domain.PageMaker;
import com.boardPractice.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService service;

    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model m){
       if(page == null) page = 1;
       if(pageSize == null) pageSize = 10;

       try{
           int totalCnt = service.totalCnt();
           PageMaker pm = new PageMaker(page, totalCnt);
           List<BoardDTO> list = service.listAll(pm);

           m.addAttribute(list);
           m.addAttribute(pm);
       }catch(Exception e){
           e.printStackTrace();
       }
       return "list";
    }

    @GetMapping("/write")
    public String write(Integer page, Integer pageSize, Model m){

        m.addAttribute("page", page);
        m.addAttribute("pageSize", pageSize);
        m.addAttribute("setting", "WRT");

        return "board";
    }

    @PostMapping("/write")
    public String write(BoardDTO boardDTO, RedirectAttributes rttr){
        try{
            System.out.println(boardDTO);
            boardDTO.setWriter("yeop");
            int resultCnt = service.write(boardDTO);
            if(resultCnt != 1) throw new Exception();
            rttr.addFlashAttribute("message", "WRT_OK");
        }catch(Exception e){
            e.printStackTrace();
            rttr.addFlashAttribute("message", "WRT_ERROR");
        }
        return "redirect:/board/list";
    }

    @GetMapping("/board")
    public String detail(Integer page, Integer pageSize, Integer bno, Model m){

        try{
            BoardDTO boardDTO = service.select(bno);
            m.addAttribute(boardDTO);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
            m.addAttribute("setting", "RED");
        }catch(Exception e){
            e.printStackTrace();
        }
        return "board";
    }

    @GetMapping("/modify")
    public String modify(Integer page, Integer pageSize, Integer bno, Model m){
        try{
            BoardDTO boardDTO = service.select(bno);
            m.addAttribute(boardDTO);
            m.addAttribute("setting", "MOD");
        }catch(Exception e){
            e.printStackTrace();
        }
        return "board";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO, Model m, RedirectAttributes rttr){
        try{
            boardDTO.setWriter("yeop");
            int resultCnt = service.modify(boardDTO);
            System.out.println("resultCnt = " + resultCnt);
            if(resultCnt != 1) throw new Exception();

            rttr.addFlashAttribute("message", "MOD_OK");
            m.addAttribute(boardDTO);

            return "redirect:/board/board";
        }catch(Exception e){
            rttr.addFlashAttribute("message", "MOD_ERR");
            m.addAttribute(boardDTO);
            e.printStackTrace();
            return "redirect:/board/modify";
        }
    }

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, RedirectAttributes rttr, Model m){
        try{
            System.out.println(bno);
            int resultCnt = service.delete(bno);
            if(resultCnt != 1) throw new Exception();

            rttr.addFlashAttribute("message", "DEL_OK");
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/board/list?page=" + page + "&pageSize=" + pageSize;
    }
}

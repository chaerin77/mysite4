package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	BoardDao boardDao;
	
	//리스트 가져오기
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		
		List<BoardVo> boardList = boardDao.getList();
		model.addAttribute("bList", boardList);
		
		return "board/list";
	}
	
	//글쓰기 폼 -로그인 했을때만 글쓰기 버튼이 보여야함
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		
		//포워드 /WEB-INF/views/board/writeForm.jsp
		return "board/writeForm";
	}
	
	//글쓰기
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write() {
		
		
		return "redirect:/board/list";
	}
	
	//읽기  -제목 누르면 글 불러오기 
	@RequestMapping(value="/read", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam("no") int no,
					   Model model) {
		
		boardDao.uphit(no);
		
		BoardVo boardVo = boardDao.getPerson(no);
		model.addAttribute("bVo", boardVo); //ds에 no가 __인 사람의 정보 넣어놓기
		
		return "board/read";
	}
	
	//삭제하기 -세션 필요
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		
		boardDao.deletePerson(no);
		
		return "redirect:/board/list";
	}
	
	//수정폼
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm() {
		
		return "";
	}
	
	//수정하기
	@RequestMapping(value="/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify() {
		
		
		return "redirect:/board/list";
	}
}

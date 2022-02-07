package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	//리스트 가져오기
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		
		//List<BoardVo> boardList = boardDao.getList();
		List<BoardVo> boardList = boardService.getList();
		model.addAttribute("bList", boardList);
		
		return "board/list";
	}
	
	//220207 리스트 가져오기 + 페이징
	@RequestMapping(value="/list2", method= {RequestMethod.GET, RequestMethod.POST})
	public String list2(Model model, 
						@RequestParam(value= "crtPage", required = false, defaultValue="1") int crtPage) {
		System.out.println("BoardController.list2");
		System.out.println(crtPage);

		//해당페이지의 글 리스트 10개
		Map<String, Object> pMap = boardService.getBoardList2(crtPage);
		
		System.out.println("-------------------------------");
		System.out.println(pMap);
		System.out.println("-------------------------------");
		
		model.addAttribute("pMap", pMap);
		
		return "board/list";
	}
	
	//글쓰기 폼 -로그인 했을때만 글쓰기 버튼이 보여야함 list.jsp 수정완료
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		
		//포워드 /WEB-INF/views/board/writeForm.jsp
		return "board/writeForm";
	}
	
	//글쓰기
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		
		boardService.insert(boardVo);
		
		return "redirect:/board/list";
	}
	
	//읽기  -제목 누르면 글 불러오기 
	@RequestMapping(value="/read", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam("no") int no, Model model) {
		/*
		boardDao.uphit(no);
		BoardVo boardVo = boardDao.getPerson(no);*/
		
		boardService.uphit(no);
		BoardVo boardVo = boardService.getPerson(no);
		model.addAttribute("bVo", boardVo); //ds에 no가 __인 사람의 정보 넣어놓기
		
		return "board/read";
	}
	
	//삭제하기 -세션 필요 list.jsp 수정완료
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		
		//boardDao.deletePerson(no);
		boardService.delete(no);
		
		return "redirect:/board/list";
	}
	
	//수정폼
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no, Model model) {
		
		//파라미터에 no값 들어있어서 그냥 포워드 시킴/ 아니지! no가_인사람의 정보 갖고와서 ds에 넣어야함
		BoardVo boardVo = boardService.getPerson(no);
		System.out.println(boardVo);
		
		model.addAttribute("bVo", boardVo);
		return "board/modifyForm";
	}
	
	//수정하기
	@RequestMapping(value="/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		
		boardService.update(boardVo);
		
		return "redirect:/board/list";
	}
}

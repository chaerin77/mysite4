package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/rboard")
public class RboardController {

	@Autowired
	private RboardService rboardService;
	
	//댓글 목록 가져오기
	@RequestMapping(value="list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		
		List<RboardVo> rboardVo = rboardService.list();
		model.addAttribute("rboardList", rboardVo);
		
		return "rboard/list";
	}
	
	//댓글등록
	@RequestMapping(value="addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(HttpSession session, @ModelAttribute RboardVo rbVo) { //insert content __ where
		
		//RboardVo rboardVo = (RboardVo)session.getAttribute("authUser");//세션의 no가 rboard에선 user_no임 아무튼 이거하면 내가로그인한사람의no,name이옴
		//String name = rboardVo.getName();
		UserVo uvo =(UserVo)session.getAttribute("authUser");
		String name = uvo.getName();
		rbVo.setName(name);
		
		rboardService.addList(rbVo);
		
		return "redirect:/rboard/list";
	}
	
	
	//답글쓰기 폼
	@RequestMapping(value="writeFormA", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		
		//group_no갖고 뭔가 해야할텐데 일단 화면 보이는것만 원하는대로 만들고 생각하자
		
		return "rboard/writeFormA";
	}
	
	//답글 등록
	
}

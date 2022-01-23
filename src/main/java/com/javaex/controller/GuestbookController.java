package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/guest")
public class GuestbookController {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	//리스트 가져오기
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String guestList(Model model){
		System.out.println("GuestbookController.list");
		//db에서 목록 갖고오는거
		
		List<GuestbookVo> guestList = guestbookDao.getList();
		
		model.addAttribute("gList", guestList);
		
		//포워드
		return "guestbook/list";
	}
	
	//리스트 추가
	@RequestMapping(value="/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(@ModelAttribute GuestbookVo gvo) {
		
		guestbookDao.addList(gvo);
		
		return "redirect:/guest/list";
	}
	
	//삭제폼
	@RequestMapping(value="/deleteForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		
		return "guestbook/deleteForm";
	}
	
	//삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no,
						 @RequestParam("password") String passowrd) { //deleteForm.jsp의 no,password값 갖고와서 guestbook.xml의 쿼리문에 넣어주기
		
		guestbookDao.deleteList(no, passowrd);
		
		return "redirect:/guest/list";
	}
	
}

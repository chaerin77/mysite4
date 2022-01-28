package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/list") //
	public String list() {  //컨트롤러를 list addlist 로 만들어놔서 jsp이름이랑 다른거 바꾸다가 못따라갈수있으니 그냥 내가 만든이름대로 가기로함
		System.out.println("ApiGuestbookController.list()");
	
		return "/aGuestbook/list"; // /WEB-INF/views/aGuestbook/list.jsp
	}
	
	@ResponseBody //json으로 
	@RequestMapping("/addList")
	public List<GuestbookVo> addList() {
		System.out.println("ApiGuestbookController.addList()");
		List<GuestbookVo> guestbookList = guestbookService.getList();
		System.out.println(guestbookList);
		
		return guestbookList;
	}
	
	@ResponseBody //응답의 body부분에 json으로 붙어서 가도록 해줘야함
	@RequestMapping("/write") //20220128 화면 유지한채로 추가된 데이터만 받을것 - 요청 ajax로할것
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.write()");
	
		//저장하고 저장된값 리턴
		GuestbookVo gVo = guestbookService.addGuestResultVo(guestbookVo);
		//받은 데이터를 json으로 바꿔서 보내야함. 응답의 body부분에 json으로 붙어서감
		System.out.println(gVo);
		return gVo;
				
	}
}

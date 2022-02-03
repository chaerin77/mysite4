package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/api/user")
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("ApiUserController.joinForm()");
		
		return "/aUser/joinForm";
	}
	
	//실패1,2
	
	@ResponseBody
	@RequestMapping("/overCheck")
	public String overCheck(@ModelAttribute UserVo userVo) {
		System.out.println("ApiUserController.overCheck");
		
		System.out.println(userVo);
		/*
		String id=userVo.getId();
		System.out.println(id);*/
		
		//이거아니면 그냥 overCheck에 userVo를넣는방법도..
		
		//입력폼에 입력한 아이디 -> id
		//만약 id == db에 저장된 id라면..
		
		//null이 반환되었을때 console.log(inputId)아예 안찍혀서 밑에 다른방법써보기로함
		
		String inputId = userService.overCheck(userVo);
		System.out.println("쿼리문 실행 결과");
		System.out.println(inputId);
		
		//아무값도 입력하지 않았을 경우
		String noId = userVo.getId();
		
		if(noId == "") {
			return "2";
		}
		
		//입력한 아이디 중복체크
		if(inputId==null) {
			return "1";
			
		}else {
			return "0";
		}
		
		
		/*
		if(inputId==null) {
			
			String t = "1";
			return t;
		}else {
			
			String f = "0";
			return f;
		}*/
		
	}
	
	//vo로 바꾸든말든 오류는 같음 다른문제..3
	/*
	@ResponseBody
	@RequestMapping("/overCheck")
	public UserVo overCheck(@ModelAttribute UserVo userVo) {

		System.out.println("ApiUserController.overCheck");
		
		System.out.println(userVo);
		
		UserVo uVo = userService.overCheck(userVo);
		System.out.println("쿼리문 실행 결과");
		System.out.println(uVo);

		return uVo;
	}*/
	
	
}

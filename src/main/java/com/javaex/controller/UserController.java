package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	//private UserDao userDao; 이제 컨트롤러에서 바로 dao로넘어가면안되고 service한테 부탁해야함
	private UserService userService;
	
	//로그인폼 만들기
	@RequestMapping(value="/user/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm");
	
		//return "user/loginForm"; //뷰 리졸브
		return "user/loginForm"; 
	}

	//로그인
	@RequestMapping(value="/user/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) { //HttpSession session 이렇게 써주면 ds가 request의 세션의 주소를 준거임 이제 여기에 값넣으면됨
		System.out.println("UserController.login");
		
		//UserVo authUser = userDao.getUser(userVo);
		UserVo authUser = userService.login(userVo);//서비스의 login이라는 메소드에게 시킴
		System.out.println(authUser);
		
		if(authUser !=null) {
			//세션에 저장
			System.out.println("로그인성공");
			session.setAttribute("authUser", authUser);
			
			//리다이렉트 메인
			return "redirect:/";
		}else {//로그인 실패
			
			System.out.println("로그인실패");
			//리다이렉트 로그인폼
			return "redirect:/user/loginForm?result=fail";
		}
		
	}	
	
	//로그아웃 - db갔다 올 필요없이 세션값 삭제후 main화면 보이기 세션값 삭제하려면 세션에접근할수있어야함 -> ds한테 세션달라고하면됨
	@RequestMapping(value="/user/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) { //ds한테 세션달라고하기
		System.out.println("UserController.logout");
		
		//세션의 정보 삭제
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}

	
	//회원가입 폼
	@RequestMapping(value="/user/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	
	//회원가입
	@RequestMapping(value="/user/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join() {
		
		System.out.println("join");
		return "user/joinOk";
	}
	
	
	//회원정보 수정폼
	
	//회원정보 수정

}

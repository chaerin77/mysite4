package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.UserService;

@Controller
@RequestMapping("/api/user")
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("ApiUserController.joinForm()");
		
		return "/aGuestbook/joinForm";
	}
	
	
}

package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //핸들러매핑에 들어갈수 있게끔 이것도 설정해야함
public class Hello {
	
	//요청이 온 주소를 갖고있어야 -> 미리 준비할수있게 핸들러매핑에 들어갈수있게 -> requestMapping
	@RequestMapping(value="/hello", method= {RequestMethod.GET, RequestMethod.POST})
	public String hello() {
		System.out.println("Hello 헬로우");
		
		return "hello";
	}
}

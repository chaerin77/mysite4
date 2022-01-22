package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.javaex.dao.GuestbookDao;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	
	
	
	

}

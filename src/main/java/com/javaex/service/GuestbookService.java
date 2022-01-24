package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao; 
	
	public List<GuestbookVo> getList(){
		
		List<GuestbookVo> guestList = guestbookDao.getList();
		
		return guestList;
	}

	public void addList(GuestbookVo guestbookVo) {
		
		guestbookDao.addList(guestbookVo);
	}
	
	public void deleteList(int no, String password) {
		
		guestbookDao.deleteList(no, password);
	}
}

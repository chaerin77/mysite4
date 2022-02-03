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
	
	//220128 방명록 글 저장-->저장글 리턴
	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {
		System.out.println("guestbookService/addGuestResultVo");
		
		//저장하기
		int count = guestbookDao.insertSelectKey(guestbookVo);
		
		//저장한 내용 가져오기
		int no = guestbookVo.getNo();
		return guestbookDao.selectGuest(no); //마이바티스에서 만들어준
		
		/*위랑 같은것
		GuestbookVo gVo = guestbookDao.selectGuest(no);
		return gVo;*/
	}
	
	
	public void deleteList(int no, String password) {
		
		guestbookDao.deleteList(no, password);
	}
	
	//220203 remove
	public String remove(GuestbookVo guestbookVo) {
		System.out.println("guestbookService/remove ajax");
		
		int count = guestbookDao.delete(guestbookVo);
		
		if(count>0) {
			return "success";
		}else {
			return "fail";
		}
	}
}

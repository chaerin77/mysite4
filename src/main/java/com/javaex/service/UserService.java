package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service //sevice 연결해주기
public class UserService {

	@Autowired//dao한테 시켜야하므로 autowired 연결시켜야함.
	private UserDao userDao;
	
	public UserVo login(UserVo userVo) {
		
		UserVo authUser = userDao.selectUser(userVo);
		return authUser; 
	}

	//회원가입
	public void join(UserVo userVo) {
		
		userDao.join(userVo);
	}
	
	//수정 폼. 사람 정보 가져오기
	public UserVo modifyForm(UserVo userVo) {
		
		UserVo uVo = userDao.getUser(userVo);
		return uVo;
	}
	
	//수정하기
	public void modify(UserVo userVo) {
		
		userDao.update(userVo);
	}
}

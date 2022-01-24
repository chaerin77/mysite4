package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository 
public class UserDao {

	@Autowired  
	private SqlSession sqlSession;
	
	//유저 정보 가져오기(로그인 시 사용)
	public UserVo selectUser(UserVo userVo) {//
		System.out.println("UserDao.getUser()");
		System.out.println(userVo);

		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo); 
		//db에서 만들어서 넣어준 UserVo라서 이름 겹치면안됨  authUser-> id,pw가 내가입력한 값인사람의 no,name정보 들어있음
		
		return authUser;
	}
	
	//회원가입
	public void join(UserVo userVo) {
		
		sqlSession.insert("user.join", userVo);
	}
	
	//수정폼 사람 정보 가져오기
	public UserVo getUser(UserVo userVo) {
		
		UserVo uVo = sqlSession.selectOne("user.getUser", userVo);
		return uVo;
	}
	
	//수정하기
	public void update(UserVo userVo) {
		
		sqlSession.update("user.update", userVo);
	}
	
}

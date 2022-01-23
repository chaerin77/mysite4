package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//리스트 가져오기
	public List<GuestbookVo> getList(){
		
		//List<GuestbookVo> getList = new ArrayList<GuestbookVo>();
		List<GuestbookVo> getList = sqlSession.selectList("guestbook.getlist");
		return getList;
	}
	
	//리스트 추가
	public int addList(GuestbookVo gVo) {
		
		int count = sqlSession.insert("guestbook.addlist", gVo);
		return count;
	}
	
	//삭제
	public int deleteList(int no, String password) {
		
		GuestbookVo gvo = new GuestbookVo(no, password);
		
		int count = sqlSession.delete("guestbook.deletelist", gvo);
		return count;
	}
	
}

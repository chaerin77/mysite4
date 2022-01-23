package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestbookVo;

@Repository
public class BoardDao {

	@Autowired
	SqlSession sqlSession;
	
	//리스트 가져오기
	public List<BoardVo> getList(){
		
		List<BoardVo> bvo = sqlSession.selectList("board.getList");//mybatis.mappers - board.xml - getList
		
		return bvo; 
	}
	
	//글쓰기
	
	//읽기- 정보 가져오기
	public BoardVo getPerson(int no) {
		
		BoardVo gvo = sqlSession.selectOne("board.getPerson", no);
		
		return gvo;
	}
	
	//조회수 증가
	public void uphit(int no) {
		
		sqlSession.update("board.uphit", no);
		
	}
	
	//삭제하기
	public void deletePerson(int no) {
		
		sqlSession.delete("board.deletePerson", no);
	}
	
	
	//수정하기
}

package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	SqlSession sqlSession;
	
	//리스트 가져오기
	public List<BoardVo> getList(){
		
		List<BoardVo> bvo = sqlSession.selectList("board.getList");//mybatis.mappers - board.xml - getList
		return bvo; 
	}
	
	//220207 리스트 가져오기(리스트+페이징)
	public List<BoardVo> selectList2(int startRnum, int endRnum){
		System.out.println("boardDao/selectList2");
		System.out.println(startRnum + "," + endRnum);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList2", map);
		//System.out.println(boardList);//요구한 페이지의 번호에 해당하는 rnum들 나오게
		
		return boardList;
	}
	
	
	
	//글쓰기
	public void insert(BoardVo boardVo) {
		
		sqlSession.insert("board.insert", boardVo);
	}
	
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
	public void delete(int no) {
		
		sqlSession.delete("board.delete", no);
	}
	
	
	//수정하기
	public void update(BoardVo boardVo) {
		
		sqlSession.update("board.update", boardVo);
	}
	
	//전체 글갯수 가져오기
	public int selectTotal() {
		System.out.println("boardDao/selectTotal");
		
		return sqlSession.selectOne("board.totalCnt");//줄 데이터 없어서 안줬음
	}
	
}

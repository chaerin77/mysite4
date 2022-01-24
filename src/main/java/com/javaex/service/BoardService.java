package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service//서비스연결
public class BoardService {

	@Autowired//dao연결
	BoardDao boardDao;
	
	//리스트 가져오기
	public List<BoardVo> getList(){
		
		List<BoardVo> boardList = boardDao.getList();
		return boardList;
	}
	
	//글쓰기(추가하기)
	public void insert(BoardVo boardVo) {
		
		boardDao.insert(boardVo);
	}
	
	//조회수 증가
	public void uphit(int no) {
		
		boardDao.uphit(no);
	}

	//글,사람 정보 가져오기
	public BoardVo getPerson(int no) {
		
		BoardVo boardVo = boardDao.getPerson(no);
		return boardVo;
	}
	
	//삭제하기
	public void delete(int no) {
		
		boardDao.delete(no);
	}
	
	//수정하기
	public void update(BoardVo boardVo) {
		
		boardDao.update(boardVo);
	}
	
}

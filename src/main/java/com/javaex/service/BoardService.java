package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//220207 리스트(리스트+페이징_
	public Map<String, Object> getBoardList2(int crtPage){//return값은 주소
		System.out.println("boardService.getBoardList2");
		
		////////////////////////////////////////
		// 리스트 가져오기
		////////////////////////////////////////
		
		//페이지당 글의 개수 변수로 두기 -개수가 바뀌었을때 여기만 수정하면됨
		int listCnt = 10;
		
		//현재페이지 처리
		crtPage = (crtPage>0) ? crtPage : (crtPage=1); //3항연산자 0보다크면 crtpage 아니면 우변
			
		/*
		if(crtPage <=0) {
			crtPage = 1;
		}*/
		
		
		//시작글 번호 crtPage-> 1이면 startrownum 1  6이면 startrownum 51 나와야함--한페이지의 글이 10개
		int startRnum = (crtPage-1)*listCnt + 1;
		
		//끝글 번호
		int endRnum = (startRnum + listCnt) - 1;
		
		List<BoardVo> boardList = boardDao.selectList2(startRnum, endRnum);
		
		
		
		////////////////////////////////////////
		// 페이징 버튼
		////////////////////////////////////////
		
		//전체 글갯수 가져오기
		int totalCnt = boardDao.selectTotal();
		System.out.println("totalCnt=" + totalCnt);
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		
		//마지막 버튼 번호**
		//1  1~5  0.2
		//2  1~5  0.4
		//3  1~5  0.6
		//5  1~5  1
		//6  6~10 1.2--> 페이지인 6 나누기 페이지버튼개수인5  
		//11 11~15
		
		//현재 페이지  나누기  5(페이지당 버튼갯수) 1/(double)5 -> 0.2  이거를 다 올림해버림 -> 1
		//int endPageBtnNo = Math.ceil(pageBtnCount)1/(double)5
		
		int endPageBtnNo = (int)( Math.ceil(crtPage/(double)pageBtnCount) )*pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		
		//다음 화살표 유무
		boolean next = false;
		if(endPageBtnNo*listCnt < totalCnt) {
			next = true;
		}else { //다음 화살표가 안보이면 마지막 버튼값을 다시 계산한다
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		//////////////////////////////
		//포장
		//////////////////////////////
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", boardList);
		
		System.out.println("-------------------------------");
		System.out.println(pMap);
		System.out.println("-------------------------------");
		
		return pMap; 
	}
	
	//글쓰기(추가하기)
	public void insert(BoardVo boardVo) {
		
		//boardDao.insert(boardVo);
		
		//페이징 데이터 추가123개
		/*
		for(int i=1; i<123; i++) {
			boardVo.setTitle(i + "번째 제목입니다.");
			boardVo.setContent(i+"번째 글입니다.");
			boardVo.setHit(0);
			boardVo.setUser_no(1);
			boardDao.insert(boardVo);
		}*/
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

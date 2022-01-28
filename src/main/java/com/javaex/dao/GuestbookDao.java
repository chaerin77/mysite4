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
	
	//방명록 글1개 가져오기
	public GuestbookVo selectGuest(int no) {
		System.out.println("guestbookDao/selectGuest");
	      
	    return sqlSession.selectOne("guestbook.selectByNo", no);//변수에 담고 그걸 리턴하는거나 바로리턴하는거나 같은것
	}

	
	
	//방명록 글 저장(selectKey) 20220128 
	public int insertSelectKey(GuestbookVo guestbookVo) {
		System.out.println("guestbookDao/insertSelectKey");
		
	
		System.out.println(guestbookVo);
		sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
		System.out.println(guestbookVo);
		
		//dao 는 한 쿼리만 갖고있어야하는데 이렇게 얘도 쓰면 섞어서 엉망으로쓰는거임
		//근데 우리화면에선 두가지 일을해야 원하는대로 나올것임 그래서 이렇게 dao안에 쿼리문여러개있으면안되고 서비스에서 나눠지도록 고쳐야함
 		//sqlSession.selectOne("guestbook.selectGuest",guestbookVo);
		
		
		return sqlSession.insert("guestbook.insertSelectKey", guestbookVo); //no=34를 넘겨주지 않아도 이미 guestbookVo에 저장되었으니까..? 그래서그냥 잘저장되었는가 성공한 개수만 return해주면된다는..
	}
	
	//삭제
	public int deleteList(int no, String password) {
		
		GuestbookVo gvo = new GuestbookVo(no, password);
		
		int count = sqlSession.delete("guestbook.deletelist", gvo);
		return count;
	}
	
}

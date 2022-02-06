package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	//db에 저장
	public void insertFile(GalleryVo gVo) {
		
		sqlSession.insert("gallery.insertFile", gVo);
	}
	
	//list 파일목록 가져오기
	public List<GalleryVo> list(){
		
		return sqlSession.selectList("gallery.list");
	}
}

package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {

	@Autowired
	private RboardDao rboardDao;
	
	public List<RboardVo> list(){
		
		List<RboardVo> rboardVo = rboardDao.selectList();
		
		return rboardVo;
	}
	
	public void addList(RboardVo rboardVo) {
		
		rboardDao.addList(rboardVo);
	}
}

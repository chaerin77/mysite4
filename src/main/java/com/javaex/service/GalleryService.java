package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	
	public GalleryVo restore(MultipartFile file) {
		System.out.println("GalleryService.restore");
		
		String saveDir = "D:\\javaStudy\\upload";
		
		//원본 파일 이름
		String orgName = file.getOriginalFilename();
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		//저장파일이름 중복안되게끔
		String saveName = System.currentTimeMillis()+ UUID.randomUUID().toString() + exName;
		
		//파일패스 생성
		String filePath = saveDir + "\\" + saveName;
		
		//파일 사이즈
		long fileSize = file.getSize();
		
		//파일 저장(업로드)
		try {
			
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		GalleryVo galleryVo = new GalleryVo(filePath, orgName, saveName, fileSize);
		
		return galleryVo;
	}
	
	//db에 저장
	public void insertFile(GalleryVo gVo) {
		System.out.println("GalleryService.insert");
		
		galleryDao.insertFile(gVo);
		
	}
	
	//list 파일목록 가져오기
	public List<GalleryVo> list(){
		
		return galleryDao.list();
	}
	
}

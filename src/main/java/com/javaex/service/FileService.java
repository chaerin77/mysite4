package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public String restore(MultipartFile file) {
		System.out.println("FileService.restore()");
		String saveDir = "D:\\javaStudy\\upload";
		
		System.out.println(file.getOriginalFilename());
		
		//파일을 하드디스크에 저장(운영내용)
		
		//파일관련 정보 추출
		//원본 파일 이름 --나중에 다운로드받을때 원본파일 이름으로 다시 줘야하니까 원본파일이름뽑아서 관리하기로함
		String orgName = file.getOriginalFilename();
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));  //subString ->내가 알려준 index이후의 값을 갖고오라
		
		//저장파일이름
		String saveName = System.currentTimeMillis()+ UUID.randomUUID().toString() + exName; //이미대문자 -> 스태틱에있는.. 뒤에 확장자까지 붙여줌  -->하드디스크에 저장될 파일의 이름 
		System.out.println(saveName);
		
		//파일패스생성- 실제 파일 위치(경로)
		String filePath = saveDir + "\\" + saveName; //경로가 바뀌게될때 대비해 경로를 변수로 빼놓기-6
		
		//파일 사이즈
		long fileSize = file.getSize(); //자료형 long형이라 long으로 받아야함
		//
		
		//파일 저장(업로드) -사용자입장에서 업로드
		try {
			
			byte[] fileData = file.getBytes(); // 이미지 데이터.
			
			
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			// 서버에다가 저장시켜주는 이동수단.
			
			
			bout.write(fileData);
			bout.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
	}
	
	//DB에 저장 -테이블만들기 과제
	
}

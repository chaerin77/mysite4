package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
@RequestMapping("/fileupload")
public class FileController {

	@Autowired
	private FileService fileService;
	
	//파일업로드 폼
	@RequestMapping("/form")
	public String form() {
		System.out.println("FileController.form()");
		
		
		return "fileupload/form";
	}

	//파일업로드 처리
	@RequestMapping("/upload")
	public String result(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("FileController.upload()");
		/*
		System.out.println(file);//이걸로 찍으면 실제로 데이터 안들어갔는데도 데이터아닌 다른정보들 모아 출력되어서 이걸로는 정보가 들어간건지 아닌지 알수없음
		System.out.println(file.getOriginalFilename());//중요 데이터 넣은거 이걸로 확인
		System.out.println(file.getSize());
		*/
		
		String saveName = fileService.restore(file);
		fileService.insertFile(file);
		model.addAttribute("saveName", saveName);
		
		return "fileupload/result";
	}
	
}

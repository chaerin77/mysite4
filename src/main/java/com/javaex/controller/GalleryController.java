package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("GalleryController.list");
		
		List<GalleryVo> galleryList = galleryService.list();
		model.addAttribute("galleryList", galleryList);
		
		return "gallery/list";
	}
	
	//등록 모달창에서 글작성,파일선택 후 
	@RequestMapping("/addList")
	public String addList(@RequestParam("selectFile") MultipartFile file, 
						  @RequestParam("content") String content,
						  @RequestParam("user_no") int user_no) {
		System.out.println("GallryController.addList");
		
		GalleryVo gVo = galleryService.restore(file);
		//System.out.println(gVo);
		gVo.setContent(content);
		gVo.setUser_no(user_no);
		
		System.out.println(gVo);
		
		galleryService.insertFile(gVo); //db에 저장
		
		return "redirect:/gallery/list";
	}
	
	//삭제하기
	public String delete() {
		
		return "";
	}
}

package com.example.fileupload.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fileupload.entity.Boardfile;
import com.example.fileupload.repository.BoardfileRepository;

@Controller
public class BoardfileController {
	
	@Autowired // 스프링이 자동으로 객체 주입
	BoardfileRepository boardfileRepository;
	
	
	// 파일 삭제
	@GetMapping("/removeBoardfile")
	public String removeBoardfile(@RequestParam(value = "fno") int fno
									, @RequestParam(value = "bno") int bno) {
		
		// 1) 파일 삭제 후
		Boardfile boardfile = boardfileRepository.findById(fno).orElse(null);
		
		// 파일 가져오기
		File f = new File("c:/project/upload/" + boardfile.getFname() + "." + boardfile.getFext());
		
		if(f.exists()) { // 파일이 있다면
			f.delete(); // 삭제
		}
		
		// 2) 테이블 Row 삭제
		boardfileRepository.deleteById(fno);
		return "redirect:/boardOne?bno="+bno;
	}
}

package com.example.mfu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mfu.dto.Board;
import com.example.mfu.dto.BoardForm;
import com.example.mfu.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired BoardService boardService;
	
	
	// 게시글 추가
	@GetMapping({"/addBoard"}) 
	public String addBoard() {
		return "addBoard";
	}
	
	// 게시글 추가 기능
	@PostMapping({"/addBoard"})
	public String addBoard(BoardForm boardForm) {
		log.info(boardForm.toString());
		// log.info("boardfile size: "+boardForm.getBoardfile().size());
		boardService.addBoard(boardForm);
		return "redirect:/";
	}
	
	
	// 게시글 목록
	@GetMapping({"/", "/boardList"}) // 두 개의 매핑값을 요청하는 건 상관없음
	public String boardList(Model model, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
										, @RequestParam(value = "rowPerPage", defaultValue = "5") int rowPerPage) {
		
		int offset = (currentPage - 1) * rowPerPage;
		/*
		List<Board> list = boardService.getBoardList(offset, rowPerPage);
		int totalCnt = boardService.getBoardCount();
		int totalPage = (totalCnt + rowPerPage - 1) / rowPerPage; 
		
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("prePage", currentPage > 1 ? currentPage - 1 : 1);
		model.addAttribute("nextPage", currentPage < totalPage ? currentPage + 1 : totalPage);
		model.addAttribute("totalPage", totalPage);
		*/
		return "boardList";
	}
	
	
	// 게시글 상세보기
	@GetMapping({"/boardOne"})
	public String boardOne() {
		return "boardOne";
	}
}

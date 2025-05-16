package com.example.mbboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mbboard.dto.Board;
import com.example.mbboard.dto.Page;
import com.example.mbboard.service.IBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired IBoardService boardService; // 인터페이스 형태로 의존성 주입 -> 디커플링
	
	// 게시글 목록
	@GetMapping("/boardList")
	public String boardList(Model model
			, @RequestParam(defaultValue = "1") int currentPage
			, @RequestParam(defaultValue = "") String searchWord) {
		
		Page p = new Page(8, currentPage, boardService.countBoardList(searchWord), searchWord);
		List<Board> list = boardService.getBoardList(p);
		log.info("boardList size: " + list.size());
		model.addAttribute("list", list); // 현재 요청에만 유효한 데이터 전송 방식
		model.addAttribute("page", p);
		return "boardList";
	}
	
	
	// 게시글 상세보기
	@GetMapping("/boardOne")
	public String boardOne(Model model, @RequestParam(value="boardNo") int boardNo) {
		Board board = boardService.getBoardOne(boardNo);
		model.addAttribute("board", board);
		return "boardOne";
	}
	
	
	// 게시글 추가
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";
	}
	
	// 게시글 추가 기능
	@PostMapping("/addBoard")
	public String addBoard(Board board) {
		boardService.addBoard(board);
		return "redirect:/boardList";
	}
	
	
	// 게시글 수정
	@GetMapping("/updateBoard")
	public String updateBoard(Model model, @RequestParam(value="boardNo") int boardNo) {
		Board board = boardService.getBoardOne(boardNo);
		model.addAttribute("board", board);
		return "updateBoard";
	}
	
	// 게시글 수정 기능
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "redirect:/boardOne?boardNo=" + board.getBoardNo();
	}
	
	
	// 게시글 삭제 기능
	@PostMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "redirect:/boardList";
	}
	
}


/*
	＃흐름 잊지 말기
	[사용자] → Controller → Service → Mapper → DB

	1. Controller (문을 여는 역할)
		: 사용자가 요청한 걸 받아서 Service한테 시킴
	   
	2. Service (비즈니스 로직 처리, 중간 관리자)
		: 어떤 순서로 처리할지 결정하고, Mapper에게 DB 작업 맡김
		
	3. Mapper (DB랑 직접 대화)
		: SQL을 실행하는 역할 (MyBatis 사용)
 */
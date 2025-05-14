package com.example.mbboard.service;

import java.util.List;

import com.example.mbboard.dto.Board;
import com.example.mbboard.dto.Page;

public interface IBoardService {
	List<Board> getBoardList(Page p); // 기능 정의만 존재
	
	Board getBoardOne(int boardNo);
	
	int addBoard(Board board);
	
	int updateBoard(Board board);
	
	int deleteBoard(Board board);
	
	int countBoardList(String searchWord);
}

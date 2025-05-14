package com.example.mbboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mbboard.dto.Board;
import com.example.mbboard.dto.Page;
import com.example.mbboard.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional // 이 클래스의 모든 메서드에 트랜잭션 적용 (예외 발생 시 롤백)
@Slf4j
public class BoardService implements IBoardService {
	@Autowired BoardMapper boardMapper; // 인터페이스 형태로 의존성 주입 -> 디커플링
	// 인터페이스만 의존하고 있기 때문에 나중에 구현을 바꾸더라도 코드 수정이 거의 필요 없음
	
	@Override
	public List<Board> getBoardList(Page p) {
		return boardMapper.selectBoardListByPage(p); // 실제 동작 구현
	}
	
	@Override
	public Board getBoardOne(int boardNo) {
		return boardMapper.selectBoardOne(boardNo);
	}
	
	@Override
	public int addBoard(Board board) {
		return boardMapper.insertBoard(board);
	}
	
	@Override
	public int updateBoard(Board board) {
		return boardMapper.updateBoard(board);
	}
	
	@Override
	public int deleteBoard(Board board) {
		return boardMapper.deleteBoardByKey(board);
	}
	
	@Override
	public int countBoardList(String searchWord) {
		return boardMapper.getBoardCount(searchWord);
	}
	
}

/*
	@Override -> 재정의
	IBoardService 인터페이스에 정의된 메서드를 
	BoardService 클래스에서 구현하고 있다는 뜻
*/
package com.example.mbboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mbboard.dto.Board;
import com.example.mbboard.dto.Page;

@Mapper
public interface BoardMapper {
	List<Board> selectBoardListByPage(Page p);

	Board selectBoardOne(int boardNo);
	
	int insertBoard(Board board);
	
	int updateBoard(Board board);
	
	int deleteBoardByKey(Board board);
	
	int getBoardCount(String searchWord);
}

package com.example.mybatistest.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
	void deleteByBoardNo(int boardNo); // mybatis는 update, delete, insert 자동 Integer를 반환
}

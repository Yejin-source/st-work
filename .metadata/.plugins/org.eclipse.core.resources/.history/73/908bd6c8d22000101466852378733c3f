package com.example.fileupload.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fileupload.entity.Board;
import com.example.fileupload.entity.BoardMapping;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	List<BoardMapping> findAllBy(); // 원하는 컬럼만 반환하기 위해 매핑타입 사용
									// Board가 아니라 BoardMapping 타입으로 받기
	BoardMapping findByBno(int bno);
}

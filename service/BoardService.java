package com.jh.service;

import java.util.ArrayList;

import com.jh.dto.BoardDto;
import com.jh.dto.BoardProfilDto;

public interface BoardService {
	
	// 게시글 목록 출력을 위한 메서드
	ArrayList<BoardProfilDto> getBoardListByPageNum(int pageNum);
	
	// 게시판 마지막페이지 구하는 메서드
	int getLastPageNumber();
	
	// 조회수 증가 메서드
	void increaseHitCount(int bno);

	// 게시글 상세 메서드
	BoardDto getBoardDetail(int bno);
	
	// 게시글 작성 메서드
	void writeBoard(BoardDto dto);
	
	// 게시글 수정 메서드
	void modifyBoard(BoardDto dto);
	
	//게시글 삭제 메서드
	void deleteBoard(int bno);
}

package com.jh.dao;

import java.util.ArrayList;

import com.jh.dto.BoardDto;
import com.jh.dto.BoardProfilDto;

public interface BoardDao { 
	ArrayList<BoardProfilDto> getBoardList(int pageNum);// 게시판 목록 조회를 위한 메서드
	void increaseHitCount(int bno);// 조회수 증가를 위한 메서드
	int lastPageNum();// 게시판 마지막 페이지 구하는 메서드(페이징 처리 위해)
	BoardDto boardDetail(int bno); // 게시글 상세 페이지를 보여주기 위한 메서드
	void insertBoard(BoardDto dto);// 게시글 작성을 위한 메서드
	void updateBoard(BoardDto dto);// 게시글 수정을 위한 메서드
	void deleteBoard(int bno); // 게시글 삭제를 위한 메서드
	
}

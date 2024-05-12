package com.jh.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jh.dao.BoardDao;
import com.jh.dto.BoardDto;
import com.jh.dto.BoardProfilDto;
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDao dao;
	
	// 게시판 목록조회를 위한 메서드 + 페이징
	// 입력 파라미터 : pageNum(정수)
	// 리턴값 : 게시글 전체리스트 (Arraylist)
	@Override
	public ArrayList<BoardProfilDto> getBoardListByPageNum(int pageNum) {
		return dao.getBoardList(pageNum);
	}

	// 마지막 페이지 구하는 메서드(페이징 처리 위해)
	// 리턴값 : 마지막 페이지번호
	@Override
	public int getLastPageNumber() {
		return dao.lastPageNum();
	}
	
	// 게시글 클릭시 조회수를 클릭시키기 위한 메서드
	// 입력파라미터 : bno(정수)
	@Override
	public void increaseHitCount(int bno) {
		dao.increaseHitCount(bno);
	}

	// 게시판 상세 페이지를 보여주기 위한 메서드
	// 입력값 : bno(정수)
	// 리턴값 : 해당 bno의 전체정보
	@Override
	public BoardDto getBoardDetail(int bno) {
		return dao.boardDetail(bno);
	}

	
	// 게시글 작성을 위한 메서드
	// 입력값 : dto (게시글 데이터)
	@Override
	public void writeBoard(BoardDto dto) {
		dao.insertBoard(dto);
	}

	// 게시글 수정을 위한 메서드
	// 입력값 : dto ( 게시글 데이터)
	@Override
	public void modifyBoard(BoardDto dto) {
		dao.updateBoard(dto);
	}

	// 게시글 삭제를 위한 메서드
	// 입력값 : bno(정수)
	@Override
	public void deleteBoard(int bno) {
		dao.deleteBoard(bno);
	}

}

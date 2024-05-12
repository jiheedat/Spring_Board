package com.jh.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jh.dto.BoardDto;
import com.jh.dto.BoardProfilDto;
@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	SqlSession sqlSession;
	
	// 게시판 목록조회를 위한 메서드 + 페이징
	// 입력 파라미터 : pageNum(정수)
	// 리턴값 : 게시글 전체리스트 (Arraylist)
	@Override
	public ArrayList<BoardProfilDto> getBoardList(int pageNum) {
		//페이징의 시작과끝이 필요. 
		int end = pageNum*20;
		int start = end-19;
		
		HashMap<String,Integer> params = new HashMap<String,Integer>();
		params.put("start",start);
		params.put("end", end);
		
		List<BoardProfilDto> list1 = sqlSession.selectList("boardMapper.boardList",params);
		ArrayList<BoardProfilDto> list2 = new ArrayList<BoardProfilDto>();
		list2.addAll(list1);
		
		return list2;
	}

	// 게시글 클릭시 조회수를 클릭시키기 위한 메서드
	// 입력파라미터 : bno(정수)
	@Override
	public void increaseHitCount(int bno) { 
		sqlSession.update("boardMapper.upHitCount",bno);
	}

	// 마지막 페이지 구하는 메서드(페이징 처리 위해)
	// 리턴값 : 마지막 페이지번호
	@Override
	public int lastPageNum() {
		int boardcount = sqlSession.selectOne("boardMapper.boardCount");
		int lastPage = boardcount/20;
		lastPage += (boardcount%20>0? 1: 0);
		
		return lastPage;
	}

	// 게시판 상세 페이지를 보여주기 위한 메서드
	// 입력값 : bno(정수)
	// 리턴값 : 해당 bno의 전체정보
	@Override
	public BoardDto boardDetail(int bno) {
		BoardDto dto = sqlSession.selectOne("boardMapper.detailBoard",bno);
		return dto;
	}

	// 게시글 작성을 위한 메서드
	// 입력값 : dto (게시글 데이터) 
	@Override
	public void insertBoard(BoardDto dto) {
		sqlSession.insert("boardMapper.insertBoard",dto);
		
	}
    
	// 게시글 수정을 위한 메서드
	// 입력값 : dto ( 게시글 데이터)
	@Override
	public void updateBoard(BoardDto dto) {
		sqlSession.update("boardMapper.updateBoard",dto);
	}

	// 게시글 삭제를 위한 메서드
	// 입력값 : bno(정수)
	@Override
	public void deleteBoard(int bno) {
		sqlSession.delete("boardMapper.deleteBoard",bno);
	}

}

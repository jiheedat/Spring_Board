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
	
	// �Խ��� �����ȸ�� ���� �޼��� + ����¡
	// �Է� �Ķ���� : pageNum(����)
	// ���ϰ� : �Խñ� ��ü����Ʈ (Arraylist)
	@Override
	public ArrayList<BoardProfilDto> getBoardList(int pageNum) {
		//����¡�� ���۰����� �ʿ�. 
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

	// �Խñ� Ŭ���� ��ȸ���� Ŭ����Ű�� ���� �޼���
	// �Է��Ķ���� : bno(����)
	@Override
	public void increaseHitCount(int bno) { 
		sqlSession.update("boardMapper.upHitCount",bno);
	}

	// ������ ������ ���ϴ� �޼���(����¡ ó�� ����)
	// ���ϰ� : ������ ��������ȣ
	@Override
	public int lastPageNum() {
		int boardcount = sqlSession.selectOne("boardMapper.boardCount");
		int lastPage = boardcount/20;
		lastPage += (boardcount%20>0? 1: 0);
		
		return lastPage;
	}

	// �Խ��� �� �������� �����ֱ� ���� �޼���
	// �Է°� : bno(����)
	// ���ϰ� : �ش� bno�� ��ü����
	@Override
	public BoardDto boardDetail(int bno) {
		BoardDto dto = sqlSession.selectOne("boardMapper.detailBoard",bno);
		return dto;
	}

	// �Խñ� �ۼ��� ���� �޼���
	// �Է°� : dto (�Խñ� ������) 
	@Override
	public void insertBoard(BoardDto dto) {
		sqlSession.insert("boardMapper.insertBoard",dto);
		
	}
    
	// �Խñ� ������ ���� �޼���
	// �Է°� : dto ( �Խñ� ������)
	@Override
	public void updateBoard(BoardDto dto) {
		sqlSession.update("boardMapper.updateBoard",dto);
	}

	// �Խñ� ������ ���� �޼���
	// �Է°� : bno(����)
	@Override
	public void deleteBoard(int bno) {
		sqlSession.delete("boardMapper.deleteBoard",bno);
	}

}

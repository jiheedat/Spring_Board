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
	
	// �Խ��� �����ȸ�� ���� �޼��� + ����¡
	// �Է� �Ķ���� : pageNum(����)
	// ���ϰ� : �Խñ� ��ü����Ʈ (Arraylist)
	@Override
	public ArrayList<BoardProfilDto> getBoardListByPageNum(int pageNum) {
		return dao.getBoardList(pageNum);
	}

	// ������ ������ ���ϴ� �޼���(����¡ ó�� ����)
	// ���ϰ� : ������ ��������ȣ
	@Override
	public int getLastPageNumber() {
		return dao.lastPageNum();
	}
	
	// �Խñ� Ŭ���� ��ȸ���� Ŭ����Ű�� ���� �޼���
	// �Է��Ķ���� : bno(����)
	@Override
	public void increaseHitCount(int bno) {
		dao.increaseHitCount(bno);
	}

	// �Խ��� �� �������� �����ֱ� ���� �޼���
	// �Է°� : bno(����)
	// ���ϰ� : �ش� bno�� ��ü����
	@Override
	public BoardDto getBoardDetail(int bno) {
		return dao.boardDetail(bno);
	}

	
	// �Խñ� �ۼ��� ���� �޼���
	// �Է°� : dto (�Խñ� ������)
	@Override
	public void writeBoard(BoardDto dto) {
		dao.insertBoard(dto);
	}

	// �Խñ� ������ ���� �޼���
	// �Է°� : dto ( �Խñ� ������)
	@Override
	public void modifyBoard(BoardDto dto) {
		dao.updateBoard(dto);
	}

	// �Խñ� ������ ���� �޼���
	// �Է°� : bno(����)
	@Override
	public void deleteBoard(int bno) {
		dao.deleteBoard(bno);
	}

}

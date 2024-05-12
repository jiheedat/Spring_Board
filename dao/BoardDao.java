package com.jh.dao;

import java.util.ArrayList;

import com.jh.dto.BoardDto;
import com.jh.dto.BoardProfilDto;

public interface BoardDao { 
	ArrayList<BoardProfilDto> getBoardList(int pageNum);// �Խ��� ��� ��ȸ�� ���� �޼���
	void increaseHitCount(int bno);// ��ȸ�� ������ ���� �޼���
	int lastPageNum();// �Խ��� ������ ������ ���ϴ� �޼���(����¡ ó�� ����)
	BoardDto boardDetail(int bno); // �Խñ� �� �������� �����ֱ� ���� �޼���
	void insertBoard(BoardDto dto);// �Խñ� �ۼ��� ���� �޼���
	void updateBoard(BoardDto dto);// �Խñ� ������ ���� �޼���
	void deleteBoard(int bno); // �Խñ� ������ ���� �޼���
	
}

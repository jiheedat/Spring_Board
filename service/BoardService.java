package com.jh.service;

import java.util.ArrayList;

import com.jh.dto.BoardDto;
import com.jh.dto.BoardProfilDto;

public interface BoardService {
	
	// �Խñ� ��� ����� ���� �޼���
	ArrayList<BoardProfilDto> getBoardListByPageNum(int pageNum);
	
	// �Խ��� ������������ ���ϴ� �޼���
	int getLastPageNumber();
	
	// ��ȸ�� ���� �޼���
	void increaseHitCount(int bno);

	// �Խñ� �� �޼���
	BoardDto getBoardDetail(int bno);
	
	// �Խñ� �ۼ� �޼���
	void writeBoard(BoardDto dto);
	
	// �Խñ� ���� �޼���
	void modifyBoard(BoardDto dto);
	
	//�Խñ� ���� �޼���
	void deleteBoard(int bno);
}

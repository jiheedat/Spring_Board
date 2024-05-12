package com.jh.dao;

import java.util.ArrayList;

import com.jh.dto.ReplyDto;

public interface ReplyDao {
	ArrayList<ReplyDto> getReplyDtoList(int bno); // ��� ����� ���� �޼���
	int replyCount(int bno); // ��� ���� ���ϱ� ���� �޼��� 
	void createReply(ReplyDto dto); // ��� �ۼ��� ���� �޼���
	void deleteReply(int rno); // ��� ������ ���� �޼���
	void replyUpdate(ReplyDto dto); //��� ������ ���� �޼���
	ReplyDto replyDetail(int rno); // ��� ������ ��ȸ�ϴ� �޼���
}

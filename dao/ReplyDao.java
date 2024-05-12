package com.jh.dao;

import java.util.ArrayList;

import com.jh.dto.ReplyDto;

public interface ReplyDao {
	ArrayList<ReplyDto> getReplyDtoList(int bno); // 댓글 출력을 위한 메서드
	int replyCount(int bno); // 댓글 수를 구하기 위한 메서드 
	void createReply(ReplyDto dto); // 댓글 작성을 위한 메서드
	void deleteReply(int rno); // 댓글 삭제를 위한 메서드
	void replyUpdate(ReplyDto dto); //댓글 수정을 위한 메서드
	ReplyDto replyDetail(int rno); // 댓글 정보를 조회하는 메서드
}

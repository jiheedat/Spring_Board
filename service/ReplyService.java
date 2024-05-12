package com.jh.service;

import java.util.ArrayList;

import com.jh.dto.ReplyDto;

public interface ReplyService {
	ArrayList<ReplyDto> getReplyDtoList(int bno);
	int replyCount(int bno);
	void createReply(ReplyDto dto);
	void deleteReply(int rno);
	void replyUpdate(ReplyDto dto);
	ReplyDto replyDetail(int rno);
}

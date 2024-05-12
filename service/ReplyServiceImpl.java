package com.jh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jh.dao.ReplyDao;
import com.jh.dto.ReplyDto;
@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	ReplyDao dao;
	
	@Override
	public ArrayList<ReplyDto> getReplyDtoList(int bno) {
		ArrayList<ReplyDto> replyDto = dao.getReplyDtoList(bno); 
		return replyDto;
	}
	@Override
	public int replyCount(int bno) {
		return dao.replyCount(bno);
		
	}

	@Override
	public void createReply(ReplyDto dto) {
		dao.createReply(dto);
	}

	@Override
	public void deleteReply(int rno) {
		dao.deleteReply(rno);
	}

	@Override
	public void replyUpdate(ReplyDto dto) {
		dao.replyUpdate(dto);
	}
	@Override
	public ReplyDto replyDetail(int rno) {
		return dao.replyDetail(rno);
	}

}

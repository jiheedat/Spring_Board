package com.jh.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jh.dto.ReplyDto;
@Repository
public class ReplyDaoImpl implements ReplyDao {
	@Autowired
	SqlSession sqlSession;
	
	// ��� ��� ����� ���� �޼���
	// �Է°� : bno(����)
	// ���ϰ� : �ش��� ��ü������(ArrayList)
	@Override
	public ArrayList<ReplyDto> getReplyDtoList(int bno) {
		List<ReplyDto> reply = sqlSession.selectList("replyMapper.replyList",bno);
		ArrayList<ReplyDto> replyDto = new ArrayList<ReplyDto>();
		replyDto.addAll(reply);
		
		return replyDto;
	}

	// ��� ���� ���ϱ� ���� �޼���
	// �Է°� : bno(����)
	// ���ϰ� : ��� ��(����)
	@Override
	public int replyCount(int bno) {
		return sqlSession.selectOne("replyMapper.replyCnt", bno) ;
	}
	
    // ����ۼ� �޼���
	// �Է°� : dto(ReplyDto)
	@Override
	public void createReply(ReplyDto dto) {
		sqlSession.insert("replyMapper.writeReply",dto);
	}

	// ��ۻ��� �޼���
	// �Է°� : rno(����)
	@Override
	public void deleteReply(int rno) {
		sqlSession.delete("replyMapper.deleteReply",rno);
	}

	// ��� ������ ���� �޼���
	// �Է°� : dto(ReplyDto)
	@Override
	public void replyUpdate(ReplyDto dto) {
		sqlSession.update("replyMapper.updateReply",dto);
	}

	// ��� ������ �޼���
	// �Է°� : rno(����)
	@Override
	public ReplyDto replyDetail(int rno) {
		return sqlSession.selectOne("replyMapper.detailReply",rno);
	}

}

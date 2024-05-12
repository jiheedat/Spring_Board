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
	
	// 댓글 목록 출력을 위한 메서드
	// 입력값 : bno(정수)
	// 리턴값 : 해당댓글 전체데이터(ArrayList)
	@Override
	public ArrayList<ReplyDto> getReplyDtoList(int bno) {
		List<ReplyDto> reply = sqlSession.selectList("replyMapper.replyList",bno);
		ArrayList<ReplyDto> replyDto = new ArrayList<ReplyDto>();
		replyDto.addAll(reply);
		
		return replyDto;
	}

	// 댓글 수를 구하기 위한 메서드
	// 입력값 : bno(정수)
	// 리턴값 : 댓글 수(정수)
	@Override
	public int replyCount(int bno) {
		return sqlSession.selectOne("replyMapper.replyCnt", bno) ;
	}
	
    // 댓글작성 메서드
	// 입력값 : dto(ReplyDto)
	@Override
	public void createReply(ReplyDto dto) {
		sqlSession.insert("replyMapper.writeReply",dto);
	}

	// 댓글삭제 메서드
	// 입력값 : rno(정수)
	@Override
	public void deleteReply(int rno) {
		sqlSession.delete("replyMapper.deleteReply",rno);
	}

	// 댓글 수정을 위한 메서드
	// 입력값 : dto(ReplyDto)
	@Override
	public void replyUpdate(ReplyDto dto) {
		sqlSession.update("replyMapper.updateReply",dto);
	}

	// 댓글 상세정보 메서드
	// 입력값 : rno(정수)
	@Override
	public ReplyDto replyDetail(int rno) {
		return sqlSession.selectOne("replyMapper.detailReply",rno);
	}

}

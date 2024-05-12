package com.jh.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jh.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{
	@Autowired
	SqlSession sqlSession;
	
	// pw확인을 위한 메서드
	// 입력값 : id(문자열)
	// 리턴값 : pw(문자열)
	@Override
	public String selectPw(String id) {
		return sqlSession.selectOne("memberMapper.selectPw",id);
	}

	// 이름확인을 위한 메서드
	// 입력값 : id(문자열)
	// 리턴값 : pw(문자열)
	@Override
	public String selectName(String id) {
		return sqlSession.selectOne("memberMapper.selectName",id);
	}
	
	// 회원가입을 위한 메서드
	// 입력값 : dto (MemberDto)
	@Override
	public void insertMember(MemberDto dto) {
		sqlSession.insert("memberMapper.join",dto);
	}

	// id를 DB정보와 확인하기 위한 메서드
	// 입력값 : id(문자열)
	@Override
	public String checkIdByDB(String id) {
		return sqlSession.selectOne("memberMapper.checkId",id);
	}

	// 멤버정보를 확인하는 메서드
	// 입력값 : id(문자열)
	// 리턴값 : MemberDto(해당 멤버데이터)
	@Override
	public MemberDto memberDto(String id) {
		return sqlSession.selectOne("memberMapper.memberDto",id) ;
	}

	// 회원상태를 변경하는 메서드 (탈퇴여부)
	// 입력값 : id(문자열)
	@Override
	public void updateMemberStatus(String id) {
		sqlSession.update("memberMapper.unregister",id);
	}

	// 회원정보 수정을 위한 메서드
	// 입력값 : dto(MemberDto)
	@Override
	public void memberModific(MemberDto dto) {
		sqlSession.update("memberMapper.modification",dto);
	}

}

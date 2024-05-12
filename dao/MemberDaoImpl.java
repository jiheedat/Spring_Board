package com.jh.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jh.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{
	@Autowired
	SqlSession sqlSession;
	
	// pwȮ���� ���� �޼���
	// �Է°� : id(���ڿ�)
	// ���ϰ� : pw(���ڿ�)
	@Override
	public String selectPw(String id) {
		return sqlSession.selectOne("memberMapper.selectPw",id);
	}

	// �̸�Ȯ���� ���� �޼���
	// �Է°� : id(���ڿ�)
	// ���ϰ� : pw(���ڿ�)
	@Override
	public String selectName(String id) {
		return sqlSession.selectOne("memberMapper.selectName",id);
	}
	
	// ȸ�������� ���� �޼���
	// �Է°� : dto (MemberDto)
	@Override
	public void insertMember(MemberDto dto) {
		sqlSession.insert("memberMapper.join",dto);
	}

	// id�� DB������ Ȯ���ϱ� ���� �޼���
	// �Է°� : id(���ڿ�)
	@Override
	public String checkIdByDB(String id) {
		return sqlSession.selectOne("memberMapper.checkId",id);
	}

	// ��������� Ȯ���ϴ� �޼���
	// �Է°� : id(���ڿ�)
	// ���ϰ� : MemberDto(�ش� ���������)
	@Override
	public MemberDto memberDto(String id) {
		return sqlSession.selectOne("memberMapper.memberDto",id) ;
	}

	// ȸ�����¸� �����ϴ� �޼��� (Ż�𿩺�)
	// �Է°� : id(���ڿ�)
	@Override
	public void updateMemberStatus(String id) {
		sqlSession.update("memberMapper.unregister",id);
	}

	// ȸ������ ������ ���� �޼���
	// �Է°� : dto(MemberDto)
	@Override
	public void memberModific(MemberDto dto) {
		sqlSession.update("memberMapper.modification",dto);
	}

}

package com.jh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jh.dao.MemberDao;
import com.jh.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao dao;

	// ȸ������ Ȯ���ϴ� �޼���
	// �Է°� : id(���ڿ�) pw(���ڿ�)
	// ���ϰ� : ��ġ���� Ȯ��(boolean)
	@Override
	public boolean checkLogin(String id, String pw) {
		String pwFromDB = dao.selectPw(id);
		if(pw!=null && pw.equals(pwFromDB)) {
			return true;
		}
		return false;
	}

	// �̸� ��ȸ�ϴ� �޼���
	// �Է°� : id(���ڿ�)
	// ���ϰ� : name(���ڿ�)
	@Override
	public String namePwByMemberId(String id) {
		return dao.selectName(id);
	}

	// ȸ������ �޼���
	// �Է°� : dto (MemberDto)
	@Override
	public void insertMember(MemberDto dto) {
		dao.insertMember(dto);
	}

	// id�� DB������ Ȯ���ϱ� ���� �޼���
	// �Է°� : id(���ڿ�)
	@Override
	public String checkId(String id) {
		return dao.checkIdByDB(id);
	}

	// ��������� Ȯ���ϴ� �޼���
	// �Է°� : id(���ڿ�)
	// ���ϰ� : MemberDto(�ش� ���������)
	@Override
	public MemberDto memberDto(String id) {
		MemberDto dto = dao.memberDto(id);
		return dto;
	}

	// ȸ�����¸� �����ϴ� �޼��� (Ż�𿩺�)
	// �Է°� : id(���ڿ�)
	@Override
	public void unregister(String id) {
		dao.updateMemberStatus(id);
	}
	
	// ȸ������ ������ ���� �޼���
	// �Է°� : dto(MemberDto)
	@Override
	public void modification(MemberDto dto) {
		dao.memberModific(dto);
	}

}

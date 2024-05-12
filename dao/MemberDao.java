package com.jh.dao;

import com.jh.dto.MemberDto;

public interface MemberDao {
	String selectPw(String id); // �н����� Ȯ���� ���� �޼���
	String selectName(String id); // �̸� Ȯ���� ���� �޼���
	void insertMember(MemberDto dto); //ȸ�������� ���� �޼���
	String checkIdByDB(String id); // id�� DB������ Ȯ���ϱ� ���� �޼���
	MemberDto memberDto(String id); // ȸ������ �޼���
	void updateMemberStatus(String id); //ȸ��Ż�� �޼���
	void memberModific(MemberDto dto); // ȸ������ ���� �޼���
	}

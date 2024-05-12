package com.jh.service;

import com.jh.dto.MemberDto;

public interface MemberService {
	
	// ȸ������ Ȯ�� �޼���
	boolean checkLogin(String id, String pw);
	
	// ������̵�κ��� �̸�, ��й�ȣ ���ϴ� �޼���
	String namePwByMemberId(String id);
	
	// ȸ������ �޼���
	void insertMember(MemberDto dto);
	
	// ���̵� Ȯ�� �޼���
	String checkId(String id);
	
	// ȸ������ ��ȸ �޼���
	MemberDto memberDto (String id);
	
	//ȸ��Ż�� �޼���
	void unregister(String id);
	
	//ȸ������ �޼���
	void modification(MemberDto dto);
}

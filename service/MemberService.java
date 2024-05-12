package com.jh.service;

import com.jh.dto.MemberDto;

public interface MemberService {
	
	// 회원정보 확인 메서드
	boolean checkLogin(String id, String pw);
	
	// 멤버아이디로부터 이름, 비밀번호 구하는 메서드
	String namePwByMemberId(String id);
	
	// 회원가입 메서드
	void insertMember(MemberDto dto);
	
	// 아이디 확인 메서드
	String checkId(String id);
	
	// 회원정보 조회 메서드
	MemberDto memberDto (String id);
	
	//회원탈퇴 메서드
	void unregister(String id);
	
	//회원수정 메서드
	void modification(MemberDto dto);
}

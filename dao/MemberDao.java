package com.jh.dao;

import com.jh.dto.MemberDto;

public interface MemberDao {
	String selectPw(String id); // 패스워드 확인을 위한 메서드
	String selectName(String id); // 이름 확인을 위한 메서드
	void insertMember(MemberDto dto); //회원가입을 위하 메서드
	String checkIdByDB(String id); // id를 DB정보와 확인하기 위한 메서드
	MemberDto memberDto(String id); // 회원정보 메서드
	void updateMemberStatus(String id); //회원탈퇴 메서드
	void memberModific(MemberDto dto); // 회원정보 수정 메서드
	}

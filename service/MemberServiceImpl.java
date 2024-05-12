package com.jh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jh.dao.MemberDao;
import com.jh.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao dao;

	// 회원정보 확인하는 메서드
	// 입력값 : id(문자열) pw(문자열)
	// 리턴값 : 일치여부 확인(boolean)
	@Override
	public boolean checkLogin(String id, String pw) {
		String pwFromDB = dao.selectPw(id);
		if(pw!=null && pw.equals(pwFromDB)) {
			return true;
		}
		return false;
	}

	// 이름 조회하는 메서드
	// 입력값 : id(문자열)
	// 리턴값 : name(문자열)
	@Override
	public String namePwByMemberId(String id) {
		return dao.selectName(id);
	}

	// 회원가입 메서드
	// 입력값 : dto (MemberDto)
	@Override
	public void insertMember(MemberDto dto) {
		dao.insertMember(dto);
	}

	// id를 DB정보와 확인하기 위한 메서드
	// 입력값 : id(문자열)
	@Override
	public String checkId(String id) {
		return dao.checkIdByDB(id);
	}

	// 멤버정보를 확인하는 메서드
	// 입력값 : id(문자열)
	// 리턴값 : MemberDto(해당 멤버데이터)
	@Override
	public MemberDto memberDto(String id) {
		MemberDto dto = dao.memberDto(id);
		return dto;
	}

	// 회원상태를 변경하는 메서드 (탈퇴여부)
	// 입력값 : id(문자열)
	@Override
	public void unregister(String id) {
		dao.updateMemberStatus(id);
	}
	
	// 회원정보 수정을 위한 메서드
	// 입력값 : dto(MemberDto)
	@Override
	public void modification(MemberDto dto) {
		dao.memberModific(dto);
	}

}

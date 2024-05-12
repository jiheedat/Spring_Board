package com.jh.dto;

public class MemberDto { //회원정보 데이터
	private String id;
	private String pw;
	private String name;
	private String email;
	private String profil;
	private char status;
	
	
	public MemberDto(String id, String pw, String name, String email, String profil, char status) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.profil = profil;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	
	
}

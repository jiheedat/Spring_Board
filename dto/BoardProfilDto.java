package com.jh.dto;

public class BoardProfilDto { //게시글 관련 정보
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String writedate;
	private int hitcount;
	private String profil;
	
	public BoardProfilDto() {}

	public BoardProfilDto(int bno, String title, String content, String writer, String writedate, int hitcount,
			String profil) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writedate = writedate;
		this.hitcount = hitcount;
		this.profil = profil;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public int getHitcount() {
		return hitcount;
	}

	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}
	
	
	
	
	
	
}

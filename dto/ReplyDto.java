package com.jh.dto;

public class ReplyDto { //댓글 관련정보
	private int rno;
	private int bno;
	private String content;
	private String replyer;
	private String writedate;
	
	public ReplyDto() {}
	
	public ReplyDto(int rno, int bno, String content, String replyer, String writedate) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.content = content;
		this.replyer = replyer;
		this.writedate = writedate;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	
	
}

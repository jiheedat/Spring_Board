package com.jh.ex03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jh.dto.ReplyDto;
import com.jh.service.MemberService;
import com.jh.service.ReplyService;

@RestController
@RequestMapping(value="/ajax", produces="application/json; charset=utf-8")
public class MyRestController {
	@Autowired
	ReplyService rs;
	@Autowired
	MemberService ms;
	
	// 댓글삭제
	@RequestMapping("/DeleteReply")
	public Map<String,String> deleteReply(@RequestBody Map<String,String> param) {
		System.out.println("RestController) param.rno : " + param.get("rno"));
		rs.deleteReply(Integer.parseInt(param.get("rno")));
		
		int bno = Integer.parseInt(param.get("bno"));
		int numOfReplies = rs.replyCount(bno);
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("result", "OKAY");
		map.put("num", numOfReplies+"");
		return map;
	}
	
	// 댓글작성
	@RequestMapping("/InsertReply")
	public Map<String,String> insertReply(@RequestBody Map<String,String> param,ReplyDto dto) {
		String content = param.get("content");
		int bno = Integer.parseInt(param.get("bno"));
		String replyer = param.get("replyer");
		dto.setReplyer(replyer);	
		dto.setBno(bno);
		dto.setContent(content);
		
		rs.createReply(dto);
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("result", "등록됬어!!!!");
		map.put("content", content);
		return map ;
	}
	
	// 댓글수정
	@RequestMapping("/UpdateReply")
	public Map<String,String> updateReply(@RequestBody Map<String,String> param, ReplyDto dto) {
		String content = param.get("content");
		int bno = Integer.parseInt(param.get("bno"));
		int rno = Integer.parseInt(param.get("rno"));
		
		dto.setBno(bno);
		dto.setContent(content);
		dto.setRno(rno);
		
		rs.replyUpdate(dto);
		
		String udContent = dto.getContent();
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("result", "OKAY");
		return map;
	}
	
	// 댓글조회
	@RequestMapping("/SelectReply")
	public List<ReplyDto> replyList (@RequestBody Map<String,String> param) {
		int bno = Integer.parseInt(param.get("bno"));
		List<ReplyDto> replyList = rs.getReplyDtoList(bno);  
		return replyList;
	}
	
	
	// 아이디 중복체크
	@RequestMapping("/idCheck")
	public Map<String,String> idCheck(@RequestBody Map<String,String> param) {
		String  idFromDB = ms.checkId(param.get("id"));
		HashMap<String,String> map = new HashMap<String,String>();
		if(idFromDB==null && param.get("id")!="") {
			map.put("idFromDB", "true");//중복x
		} else if(idFromDB!=null && param.get("id")!=""){
			map.put("idFromDB", "false");
		}
		return map;
	} 
	
	// 댓글 수 조회
	 @RequestMapping("/ReplyCount") 
	 public Map<String,String> replyCount(@RequestBody Map<String,String> param) {
		 int bno = Integer.parseInt(param.get("bno"));
		 int numOfReplies = rs.replyCount(bno);
		 
		 HashMap<String,String> mapRet = new HashMap<String,String>();
		 mapRet.put("num", numOfReplies+"");
		 return mapRet;
	 }
}

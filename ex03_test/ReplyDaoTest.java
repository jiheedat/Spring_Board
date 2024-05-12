//package com.jh.ex03;
//
//import java.util.ArrayList;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.jh.dao.ReplyDao;
//import com.jh.dto.ReplyDto;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//
//public class ReplyDaoTest {
//	@Autowired
//	ReplyDao dao;
//	
//	@Test
//	public void testReplyList()throws Exception {
//		int bno = 158;
//		ArrayList<ReplyDto> dto = dao.getReplyDtoList(bno);
//		for(int i=0;i<dto.size();i++) {
//			System.out.println(dto.get(i).getContent());
//		}
//	}
//	
//	@Test
//	public void testReplyCnt() throws Exception {
//		int bno = 158;
//		int cnt = dao.replyCount(bno);
//		System.out.println("댓글의 수 : " + cnt);
//	}
//	
//	@Test
//	public void testWirteReply()throws Exception {
//		dao.createReply(new ReplyDto(0, 239, "1", "ygyg12", null));
//	}
//	
//	@Test
//	public void testDelte()throws Exception {
//		dao.deleteReply(22);
//	}
//	
//	@Test
//	public void testUpdate()throws Exception {
//		dao.replyUpdate(new ReplyDto(1, 300, "제발제발","지희", "제발"));
//	}
//	
//	@Test
//	public void testGetDetailReply()throws Exception {
//		ReplyDto dto = dao.replyDetail(1);
//		System.out.println(dto.getBno());
//		System.out.println(dto.getContent());
//	}
//}		
//

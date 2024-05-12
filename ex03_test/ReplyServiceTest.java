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
//import com.jh.dto.ReplyDto;
//import com.jh.service.ReplyService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//
//public class ReplyServiceTest {
//	@Autowired
//	ReplyService rs;
//	
//	@Test
//	public void testReplyList()throws Exception {
//		int bno = 158;
//		ArrayList<ReplyDto> dto = rs.getReplyDtoList(bno);
//		for(int i=0;i<dto.size();i++) {
//			System.out.println(dto.get(i).getContent());
//		}
//	}
//	
//	@Test
//	public void testReplyCnt() throws Exception {
//		int bno = 158;
//		int cnt = rs.replyCount(bno);
//		System.out.println("´ñ±ÛÀÇ ¼ö : " + cnt);
//	}
//	
//	@Test
//	public void testWirteReply()throws Exception {
//		rs.createReply(new ReplyDto(0, 300, "´ñ±ÛÅ×½ºÆ®", "HJ64", null));
//	}
//	
//	@Test
//	public void testDelte()throws Exception {
//		rs.deleteReply(32);
//	}
//	
//	@Test
//	public void testUpdate()throws Exception {
//		rs.replyUpdate(new ReplyDto(1, 300,"´ñ±Û¼öÁ¤ Å×½ºÆ® ¼º°ø!!", "Àå¿ë¶×", null));
//	}
//	
//	@Test
//	public void testDetail()throws Exception{
//		ReplyDto dto = rs.replyDetail(1);
//		System.out.println(dto.getContent());
//	}
//}

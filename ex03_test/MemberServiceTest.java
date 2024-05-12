//package com.jh.ex03;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.jh.dto.MemberDto;
//import com.jh.service.MemberService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//
//public class MemberServiceTest {
//	@Autowired
//	MemberService ms;
//	
//	@Test
//	public void testLogin() throws Exception {
//		String id = "JIHEE";
//		String pw = "pass123";
//		
//		System.out.println(ms.checkLogin(id, pw));
//	}
//	@Test
//	public void testSelectName() throws Exception {
//		System.out.println(ms.namePwByMemberId("JIHEE"));
//		
//	}
//	
//	@Test
//	public void testCheckId()throws Exception {
//		System.out.println(ms.checkId("jiee"));
//	}
//	@Test
//	public void testMemberDto() throws Exception {
//		MemberDto dto = ms.memberDto("ygyg12");
//		System.out.println(dto.getEmail());
//	}
//	
//	@Test
//	public void testStatus()throws Exception {
//		String id = "ygyg12";
//		ms.unregister(id);
//	}
//	
//	@Test
//	public void testMemberDtoup()throws Exception {
//		MemberDto dto = new MemberDto("ygyg12", "1234", "¿µ°É½Ü", "ygyg12@aaa.com", "fdf", 'Y');
//		ms.modification(dto);
//	}
//	
//	
//}

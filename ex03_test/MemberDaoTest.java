//package com.jh.ex03;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.jh.dao.MemberDao;
//import com.jh.dto.MemberDto;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//
//public class MemberDaoTest {
//	@Autowired
//	MemberDao dao;
//	
//	@Test
//	public void testSelectPw() throws Exception {
//		String id = "JIHEE";
//		String pw = dao.selectPw(id);
//		System.out.println("id: " + id);
//		System.out.println("pw: " + pw);
//	}
//	@Test
//	public void testSelectName() throws Exception {
//		String id ="JIHEE";
//		String name = dao.selectName(id);
//		System.out.println("id : " + id);
//		System.out.println("name : " + name);
//	}
//	/*
//	 * @Test public void testInsertMember()throws Exception { dao.insertMember(new
//	 * MemberDto("Àå¿ë¶×", "0618", "Àå¿ëÁØ", "asdf@@@")); }
//	 */
//	
//	@Test
//	public void testCheckid() throws Exception {
//		System.out.println(dao.checkIdByDB("jihee"));
//	}
//	
//	@Test
//	public void testCheckMember()throws Exception {
//		String id="ygyg12";
//		System.out.println(dao.memberDto(id));
//	}
//	
//	@Test
//	public void testunregister()throws Exception {
//		String id="ygyg12";
//		dao.updateMemberStatus(id);
//	}
//	
//	@Test
//	public void testMemberModific ()throws Exception {
//		String id = "ygyg12";
//		MemberDto dto = new MemberDto(id,"pass123","¿µ°É½Ü", "AA@com","cc", 'Y');
//		dao.memberModific(dto);
//	}
//		
//}

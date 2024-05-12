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
//import com.jh.dao.BoardDao;
//import com.jh.dto.BoardDto;
//import com.jh.dto.BoardProfilDto;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//
//public class BoardDaoTest {
//	@Autowired
//	BoardDao dao;
//	
//	@Test
//	public void testGetBoardList()throws Exception {
//		int pageNum = 1;
//		ArrayList<BoardProfilDto> list = dao.getBoardList(pageNum);
//		for(int i=0;i<=list.size()-1;i++) {
//			System.out.println(i+1 + " ) " + list.get(i).getBno());
//		}
//		System.out.println(dao.getBoardList(pageNum));
//	}
//	@Test
//	public void testHitCount()throws Exception {
//		int bno = 2000;
//		dao.increaseHitCount(bno);
//	}
//	@Test
//	public void testLastPage()throws Exception {
//		System.out.println(dao.lastPageNum());
//	}
//	@Test
//	public void testDetailBoard()throws Exception {
//		BoardDto dto = dao.boardDetail(118);
//		System.out.println(dto.getBno());
//	}
//	@Test
//	public void testInsertBoard()throws Exception {
//		dao.insertBoard(new BoardDto(0, "오늘은 게시판", "카페에서 작성중", "JIHEE", null, 0));
//	}
//	@Test
//	public void testUpdateBoard()throws Exception {
//		dao.updateBoard(new BoardDto(133, "오늘은 게시판", "카페에서 작성중(ud)", "JIHEE", null, 0));
//	}
//	@Test
//	public void testDeleteBoard()throws Exception {
//		dao.deleteBoard(3000);
//	}
//	
//}

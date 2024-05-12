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
//import com.jh.dto.BoardDto;
//import com.jh.dto.BoardProfilDto;
//import com.jh.service.BoardService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//
//public class BoardServiceTest {
//	@Autowired
//	BoardService bs;
//	
//	@Test
//	public void testGetDtoListByPageNum()throws Exception {
//		int pageNum = 2;
//		ArrayList<BoardProfilDto> list = bs.getBoardListByPageNum(pageNum);
//		for(BoardProfilDto dto : list) {
//			System.out.println("�Խñ� ��ȣ : " + dto.getBno());
//			System.out.println("���� : " +dto.getTitle());
//			System.out.println("���� : "+ dto.getContent());
//			System.out.println("��ȸ�� : " +dto.getHitcount());
//			System.out.println("============================================");
//		}
//	}
//	@Test
//	public void testGetLastPageNum()throws Exception {
//		System.out.println("lastPageNum : " +bs.getLastPageNumber());
//	}
//	@Test
//	public void increaseHitCount()throws Exception {
//		int bno = 133;
//		bs.increaseHitCount(bno);
//	}
//	@Test
//	public void testGetBoardDetail()throws Exception{
//		int bno = 118;
//		BoardDto dto = bs.getBoardDetail(bno);
//		System.out.println("�Խñ� ��ȣ : " + dto.getBno());
//		System.out.println("���� : " +dto.getTitle());
//		System.out.println("���� : "+ dto.getContent());
//		System.out.println("��ȸ�� : " +dto.getHitcount());
//	}
//	@Test
//	public void insertBoard()throws Exception {
//		bs.writeBoard(new BoardDto(0, "�Խ����׽�Ʈ", "�Խ����׽�Ʈ ���Դϴ�", "������", null, 0));
//	}
//	@Test
//	public void testUpdateBoard()throws Exception {
//		bs.modifyBoard(new BoardDto(134, "�Խ����׽�Ʈ(����)", "�Խ����׽�Ʈ �Դϴ�", "�����", null, 0));
//	}
//	@Test
//	public void testDeleteBoard()throws Exception {
//		bs.deleteBoard(700);
//	}
//	
//	
//}

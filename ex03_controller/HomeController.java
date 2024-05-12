package com.jh.ex03;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jh.dto.BoardDto;
import com.jh.dto.BoardProfilDto;
import com.jh.dto.MemberDto;
import com.jh.dto.ReplyDto;
import com.jh.service.BoardService;
import com.jh.service.MemberService;
import com.jh.service.ReplyService;

@Controller
public class HomeController { 

	@Autowired
	MemberService ms;
	@Autowired
	BoardService bs;
	@Autowired
	ReplyService rs;
	@Autowired
	HttpSession session;
	@Autowired
	ServletContext application;
	@Autowired
	ResourceLoader rsLoader;
	
	// index + 로그인
	@RequestMapping("/") 
	public String home(HttpSession session, Model model) {
		String msg = null;
		if(session.getAttribute("msg")!=null) {
			msg = (String)session.getAttribute("msg");
			session.removeAttribute("msg");
			model.addAttribute("msg", msg);
		}
		return "Login";
	}
	
	// 회원가입
	@RequestMapping("/Join") 
	public String joinForm() {
		return "Join";
	}

	// 회원가입 완료
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String joinImpl(MemberDto dto, Model model) {
		model.addAttribute("msg", "회원가입이 완료되었습니다");
		ms.insertMember(dto);
		return "Login";

	}
	
	// 로그인정보 확인
	@RequestMapping("/LoginCheck")
	public String logincheck(Model model, String id, String pw, HttpSession session) {
		boolean result = false;
		result = ms.checkLogin(id, pw);
		MemberDto dto = ms.memberDto(id);
		if (result&&dto.getStatus()=='Y') {
			session.setAttribute("loginId", id);
			return "redirect:/BoardList";
		}
		model.addAttribute("msg", "로그인 정보를 확인해주세요");
		return "forward:/";
	}

	// 로그아웃
	@RequestMapping("/Logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// 게시판 전체목록 출력
	@RequestMapping("/BoardList")
	public String boardList(Integer page, Model model) {
		if (page == null)
			page = 1;
		ArrayList<BoardProfilDto> list = bs.getBoardListByPageNum(page);
		int lastPage = bs.getLastPageNumber();
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("dtoList", list);
		System.out.println(list);
		return "BoardList";
	}

	// 게시글 작성form
	@RequestMapping("/WriteForm")
	public String writeForm() {
		return "WriteForm";
	}

	// 게시글 작성
	@RequestMapping("/BoardWrite")
	public String writeBoard(BoardDto dto) {
		String writer = (String)session.getAttribute("loginId");
		dto.setWriter(writer);
		bs.writeBoard(dto);
		return "redirect:/BoardList";
	}

	// 게시글 상세정보
	@RequestMapping("/BoardDetail")
	public String boardDetail(Model model, Integer bno) {
		bs.increaseHitCount(bno);
		ArrayList<ReplyDto> replyList = rs.getReplyDtoList(bno);
		BoardDto detailDto = bs.getBoardDetail(bno);
		String id =(String)session.getAttribute("loginId");
		MemberDto memberDto = ms.memberDto(id);
		int replyCnt = rs.replyCount(bno);
		String profil = null;
		if( detailDto!=null 
			&& detailDto.getWriter()!=null
			&& ms.memberDto(detailDto.getWriter())!=null) {
			profil = ms.memberDto(detailDto.getWriter()).getProfil();
		}
		model.addAttribute("memberDto",memberDto);
		model.addAttribute("profil", profil);
		model.addAttribute("replyCnt", replyCnt);
		model.addAttribute("replyList", replyList);
		model.addAttribute("detailDto", detailDto);
		model.addAttribute("bno", bno);
		return "BoardDetail";
	}

	// 게시글 삭제
	@RequestMapping("/Delete")
	public String deleteBoard(int bno) {
		bs.deleteBoard(bno);
		return "BoardDelete";
	}

	// 게시글 수정form
	@RequestMapping("/UpdateForm")
	public String updateBoardForm(Model model, Integer bno) {
		BoardDto updateDto = bs.getBoardDetail(bno);
		model.addAttribute("updateDto", updateDto);
		model.addAttribute("bno", bno);
		return "UpdateForm";
	}

	// 게시글 수정 
	@RequestMapping("/UpdateBoard")
	public String updateBoardAction(BoardDto dto, Integer bno, Model model) {
		bs.modifyBoard(dto);
		model.addAttribute("bno", bno);
		return "UpdateBoard";
	}

	// 댓글삭제
	@RequestMapping("/ReplyDelete")
	public String deleteReply(Model model, int rno, boolean result) {
		if (result) {
			rs.deleteReply(rno);
		}
		model.addAttribute("delete", "삭제가 완료되었습니다");
		return "ReplyDelete";
	}

	// 댓글작성
	@RequestMapping("/ReplyInsert")
	public String replyInsert(HttpSession session, ReplyDto dto) {
		dto.setReplyer((String) session.getAttribute("loginId"));
		rs.createReply(dto);
		return "ReplyInsert";
	}
	
	// 회원정보 수정form
	@RequestMapping("/ModificationForm")
	public String memberModificationForm(HttpSession session, Model model) {
		String id = (String) session.getAttribute("loginId");
		MemberDto memberDetail = ms.memberDto(id);
		model.addAttribute("memberDetail", memberDetail);
		return "ModificationForm";
	}
	
	//회원정보 수정
	@RequestMapping(value="Modification", method=RequestMethod.POST)
	public String memberModification(@RequestParam("file") MultipartFile file,
   			@RequestParam("id") String id,
   			@RequestParam("pw") String pw,
   			@RequestParam("name") String name,
   			@RequestParam("email") String email
   			
	) {
	System.out.println(id + " / " + pw + " / " + name + " / " + email );
	
	String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
	long size = file.getSize(); //파일 사이즈
	
	System.out.println("파일명 : "  + fileRealName);
	System.out.println("용량크기(byte) : " + size);
	//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
	if(!(size==0 || "".equals(fileRealName))) {
	String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
	//String uploadFolder = "C:\\test\\upload";
	
	/*
	  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가 
	  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다. 
	  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
	  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
	 */
	UUID uuid = UUID.randomUUID();
	System.out.println(uuid.toString());
	String[] uuids = uuid.toString().split("-");
	
	String uniqueName = uuids[0];
	/*
	 * System.out.println("생성된 고유문자열" + uniqueName); System.out.println("확장자명" +
	 * fileExtension);
	 */
	String profil = uniqueName + fileExtension;
	
	
	// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
	
	//File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
	String web_path = "/resources/upload/";
	String uploadFolder = application.getRealPath(web_path);
	System.out.println("real path : " + uploadFolder);
	
	File saveFile = new File(uploadFolder, uniqueName + fileExtension);
	
	try {
		file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
	} catch (IllegalStateException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	MemberDto dto = new MemberDto(id, pw, name, email, profil, 'Y');
	ms.modification(dto);
	} else {
		MemberDto dto = new MemberDto(id, pw, name, email, "", 'Y');
		ms.modification(dto);
	}
		session.setAttribute("msg","회원정보 수정이 완료되었습니다. 다시 로그인 해주세요");
		return "redirect:/";
	}

	// 회원탈퇴
	@RequestMapping("/DeleteMembership")
	public String deleteMember(HttpSession session, Model model, String id) {
		//MemberDto memberDto = ms.memberDto(id);
		ms.unregister(id);
		session.setAttribute("msg","탈퇴되었습니다");
		return "redirect:/";
	}
	
	// 파일 업로드
	@RequestMapping("/upload")
	public void form() {}
	
	@RequestMapping(value="/Register", method=RequestMethod.POST)
	public String upload(HttpSession session,
						@RequestParam("file") MultipartFile file,
			   			@RequestParam("id") String id,
			   			@RequestParam("pw") String pw,
			   			@RequestParam("name") String name,
			   			@RequestParam("email") String email
			   			
			) {
		System.out.println(id + " / " + pw + " / " + name + " / " + email);
		
		String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
		long size = file.getSize(); //파일 사이즈
		
		System.out.println("파일명 : "  + fileRealName);
		System.out.println("용량크기(byte) : " + size);
		//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
		if( !(size==0 || "".equals(fileRealName)) ) {
			String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
			//String uploadFolder = "C:\\test\\upload";
			
			
			/*
			  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가 
			  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다. 
			  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
			  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
			 */
			UUID uuid = UUID.randomUUID();
			System.out.println(uuid.toString());
			String[] uuids = uuid.toString().split("-");
			
			String uniqueName = uuids[0];
			/*
			 * System.out.println("생성된 고유문자열" + uniqueName); System.out.println("확장자명" +
			 * fileExtension);
			 */
			
			String profil = uniqueName + fileExtension;
			/*
			 * if(file==null) { profil = null; }
			 */
			
			// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
			
			//File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
			String web_path = "/resources/upload/";  // --> /BoardMVC/resources/upload/
	        String uploadFolder = application.getRealPath(web_path);
	        System.out.println("real path : " + uploadFolder);
	        
			File saveFile = new File(uploadFolder, uniqueName + fileExtension);
			try {
				file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			MemberDto dto = new MemberDto(id, pw, name, email, profil, 'Y');
			ms.insertMember(dto);
		} else {
			MemberDto dto = new MemberDto(id, pw, name, email, "", 'Y');
			ms.insertMember(dto);
		}
		
		session.setAttribute("msg", "회원가입이 완료되었습니다");
		return "redirect:/";
	}
}
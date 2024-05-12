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
	
	// index + �α���
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
	
	// ȸ������
	@RequestMapping("/Join") 
	public String joinForm() {
		return "Join";
	}

	// ȸ������ �Ϸ�
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String joinImpl(MemberDto dto, Model model) {
		model.addAttribute("msg", "ȸ�������� �Ϸ�Ǿ����ϴ�");
		ms.insertMember(dto);
		return "Login";

	}
	
	// �α������� Ȯ��
	@RequestMapping("/LoginCheck")
	public String logincheck(Model model, String id, String pw, HttpSession session) {
		boolean result = false;
		result = ms.checkLogin(id, pw);
		MemberDto dto = ms.memberDto(id);
		if (result&&dto.getStatus()=='Y') {
			session.setAttribute("loginId", id);
			return "redirect:/BoardList";
		}
		model.addAttribute("msg", "�α��� ������ Ȯ�����ּ���");
		return "forward:/";
	}

	// �α׾ƿ�
	@RequestMapping("/Logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// �Խ��� ��ü��� ���
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

	// �Խñ� �ۼ�form
	@RequestMapping("/WriteForm")
	public String writeForm() {
		return "WriteForm";
	}

	// �Խñ� �ۼ�
	@RequestMapping("/BoardWrite")
	public String writeBoard(BoardDto dto) {
		String writer = (String)session.getAttribute("loginId");
		dto.setWriter(writer);
		bs.writeBoard(dto);
		return "redirect:/BoardList";
	}

	// �Խñ� ������
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

	// �Խñ� ����
	@RequestMapping("/Delete")
	public String deleteBoard(int bno) {
		bs.deleteBoard(bno);
		return "BoardDelete";
	}

	// �Խñ� ����form
	@RequestMapping("/UpdateForm")
	public String updateBoardForm(Model model, Integer bno) {
		BoardDto updateDto = bs.getBoardDetail(bno);
		model.addAttribute("updateDto", updateDto);
		model.addAttribute("bno", bno);
		return "UpdateForm";
	}

	// �Խñ� ���� 
	@RequestMapping("/UpdateBoard")
	public String updateBoardAction(BoardDto dto, Integer bno, Model model) {
		bs.modifyBoard(dto);
		model.addAttribute("bno", bno);
		return "UpdateBoard";
	}

	// ��ۻ���
	@RequestMapping("/ReplyDelete")
	public String deleteReply(Model model, int rno, boolean result) {
		if (result) {
			rs.deleteReply(rno);
		}
		model.addAttribute("delete", "������ �Ϸ�Ǿ����ϴ�");
		return "ReplyDelete";
	}

	// ����ۼ�
	@RequestMapping("/ReplyInsert")
	public String replyInsert(HttpSession session, ReplyDto dto) {
		dto.setReplyer((String) session.getAttribute("loginId"));
		rs.createReply(dto);
		return "ReplyInsert";
	}
	
	// ȸ������ ����form
	@RequestMapping("/ModificationForm")
	public String memberModificationForm(HttpSession session, Model model) {
		String id = (String) session.getAttribute("loginId");
		MemberDto memberDetail = ms.memberDto(id);
		model.addAttribute("memberDetail", memberDetail);
		return "ModificationForm";
	}
	
	//ȸ������ ����
	@RequestMapping(value="Modification", method=RequestMethod.POST)
	public String memberModification(@RequestParam("file") MultipartFile file,
   			@RequestParam("id") String id,
   			@RequestParam("pw") String pw,
   			@RequestParam("name") String name,
   			@RequestParam("email") String email
   			
	) {
	System.out.println(id + " / " + pw + " / " + name + " / " + email );
	
	String fileRealName = file.getOriginalFilename(); //���ϸ��� �� �� �ִ� �޼���!
	long size = file.getSize(); //���� ������
	
	System.out.println("���ϸ� : "  + fileRealName);
	System.out.println("�뷮ũ��(byte) : " + size);
	//������ ������ �����̸� fileextension���� .jsp�̷�����  Ȯ���� ���� ����
	if(!(size==0 || "".equals(fileRealName))) {
	String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
	//String uploadFolder = "C:\\test\\upload";
	
	/*
	  ���� ���ε�� ���ϸ��� ������ ������ �̹� ������ ���� �ְ� ����ڰ� 
	  ���ε� �ϴ� ���ϸ��� ��� �̿��� ���� �Ǿ����� �� �ֽ��ϴ�. 
	  Ÿ�ξ �������� �ʴ� ȯ�濡���� ���� ������ ���� �ʽ��ϴ�.(�������� ��ǥ���� ����)
	  ������ ���� ���ڸ� ���� db�� ������ ������ ���ϸ��� ���Ӱ� ����� �ش�.
	 */
	UUID uuid = UUID.randomUUID();
	System.out.println(uuid.toString());
	String[] uuids = uuid.toString().split("-");
	
	String uniqueName = uuids[0];
	/*
	 * System.out.println("������ �������ڿ�" + uniqueName); System.out.println("Ȯ���ڸ�" +
	 * fileExtension);
	 */
	String profil = uniqueName + fileExtension;
	
	
	// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid ���� ��
	
	//File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // ���� ��
	String web_path = "/resources/upload/";
	String uploadFolder = application.getRealPath(web_path);
	System.out.println("real path : " + uploadFolder);
	
	File saveFile = new File(uploadFolder, uniqueName + fileExtension);
	
	try {
		file.transferTo(saveFile); // ���� ���� ����޼���(filewriter �۾��� �ս��� �ѹ濡 ó�����ش�.)
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
		session.setAttribute("msg","ȸ������ ������ �Ϸ�Ǿ����ϴ�. �ٽ� �α��� ���ּ���");
		return "redirect:/";
	}

	// ȸ��Ż��
	@RequestMapping("/DeleteMembership")
	public String deleteMember(HttpSession session, Model model, String id) {
		//MemberDto memberDto = ms.memberDto(id);
		ms.unregister(id);
		session.setAttribute("msg","Ż��Ǿ����ϴ�");
		return "redirect:/";
	}
	
	// ���� ���ε�
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
		
		String fileRealName = file.getOriginalFilename(); //���ϸ��� �� �� �ִ� �޼���!
		long size = file.getSize(); //���� ������
		
		System.out.println("���ϸ� : "  + fileRealName);
		System.out.println("�뷮ũ��(byte) : " + size);
		//������ ������ �����̸� fileextension���� .jsp�̷�����  Ȯ���� ���� ����
		if( !(size==0 || "".equals(fileRealName)) ) {
			String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
			//String uploadFolder = "C:\\test\\upload";
			
			
			/*
			  ���� ���ε�� ���ϸ��� ������ ������ �̹� ������ ���� �ְ� ����ڰ� 
			  ���ε� �ϴ� ���ϸ��� ��� �̿��� ���� �Ǿ����� �� �ֽ��ϴ�. 
			  Ÿ�ξ �������� �ʴ� ȯ�濡���� ���� ������ ���� �ʽ��ϴ�.(�������� ��ǥ���� ����)
			  ������ ���� ���ڸ� ���� db�� ������ ������ ���ϸ��� ���Ӱ� ����� �ش�.
			 */
			UUID uuid = UUID.randomUUID();
			System.out.println(uuid.toString());
			String[] uuids = uuid.toString().split("-");
			
			String uniqueName = uuids[0];
			/*
			 * System.out.println("������ �������ڿ�" + uniqueName); System.out.println("Ȯ���ڸ�" +
			 * fileExtension);
			 */
			
			String profil = uniqueName + fileExtension;
			/*
			 * if(file==null) { profil = null; }
			 */
			
			// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid ���� ��
			
			//File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // ���� ��
			String web_path = "/resources/upload/";  // --> /BoardMVC/resources/upload/
	        String uploadFolder = application.getRealPath(web_path);
	        System.out.println("real path : " + uploadFolder);
	        
			File saveFile = new File(uploadFolder, uniqueName + fileExtension);
			try {
				file.transferTo(saveFile); // ���� ���� ����޼���(filewriter �۾��� �ս��� �ѹ濡 ó�����ش�.)
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
		
		session.setAttribute("msg", "ȸ�������� �Ϸ�Ǿ����ϴ�");
		return "redirect:/";
	}
}
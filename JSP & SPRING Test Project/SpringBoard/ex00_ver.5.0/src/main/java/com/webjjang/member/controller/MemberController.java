package com.webjjang.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webjjang.member.service.MemberService;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.member.vo.MemberVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.file.FileUtil;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {

	// 자동 DI
	@Autowired
	private MemberService service;
	
	// 로그인 폼
	@GetMapping("/login.do")
	public String loginForm() throws Exception {
		log.info("login 폼으로 이동");
		return "member/login";
	}
	
	// 로그인 처리
	@PostMapping("/login.do")
	// 사용자가 아이디와 비밀번호를 입력해서 보낸다. -> 받는다.
	public String login(LoginVO invo, HttpSession session) throws Exception  {
		
		log.info("로그인 처리 - invo : " + invo);
		
		session.setAttribute("login", service.login(invo));
		
		// 원래는 main으로 보내야하나 main을 안 만들어서 만들어진 게시판으로 임시로 보낸다.
		return "redirect:/board/list.do";
	}
	
	// 로그아웃 처리
	@GetMapping("/logout.do")
	public String logout(HttpSession session) throws Exception {
		// 로그아웃 처리 - session의 정보를 지운다.
		session.removeAttribute("login");
		
		log.info("로그아웃 처리됨");
		
		// 원래는 main으로 보내야하나 main을 안 만들어서 만들어진 게시판으로 임시로 보낸다.
		return "redirect:/board/list.do";
	}
	
	// 회언 리스트 - 관리자만 가능
	@GetMapping("/list.do")
	// @ModelAttribute 변수 - model에 담긴 변수로 처리해 준다.->jsp 까지 전달이 된다.
	public String list(@ModelAttribute PageObject pageObject, Model model) throws Exception  {
		
		model.addAttribute("list", service.list(pageObject));
		
		return "member/list";
	}
	
	// 회원정보보기 / 내 정보보기
	@GetMapping("/view.do")
	public String view(String id, Model model, HttpSession session) throws Exception {
		
		if(id == null) {
			// 내정보보기
			model.addAttribute("title", "내 정보 보기");
			id = ((LoginVO) session.getAttribute("login")).getId();
		} else {
			// 회원관리 - 회원정보보기
			model.addAttribute("title", "회원 정보 보기");
		}
		model.addAttribute("vo", service.view(id));
		
		return "member/view";
	}
	
	// 회원가입 폼
	@GetMapping("/write.do")
	public String writeForm() throws Exception{
		return "member/write";
	}
	
	// 회원가입 처리
	@PostMapping("/write.do")
	public String write(MemberVO vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		// 회원 사진을 저장 위치
		String path = "/upload/member";
		
		// 서버에 파일 저장하기 -> 서버에 저장된 파일명을 받아서 photo에 넣는다.
		vo.setPhoto(FileUtil.upload(path, vo.getPhotoFile(), request));
		
		// 회원 가입 처리
		service.write(vo);
		
		// redirect 하는 페이지에서 한번만 사용되는 속성값을 전달할 수 있다. -> session
		rttr.addFlashAttribute("msg", "성공적으로 회원가입이 되셨습니다. \\n로그인 후 사용하세요.");
		
		return "redirect:/member/login.do";
	}
	
	// 아이디 중복 체크
	@GetMapping("/idCheck")
	public String idCheck(String id, Model model) throws Exception{
		
		model.addAttribute("id", service.idCheck(id));
		
		return "member/idCheck";
	}
	
	// 상태 변경
	@PostMapping("/changeStatus.do")
	public String changeStatus(PageObject pageObject, MemberVO vo) throws Exception{
		
		// DB 에서 상태 변경을 시킨다.
		service.changeStatus(vo);
		
		return "redirect:view.do?id=" + vo.getId() + "&page=" + pageObject.getPage() + "&perPageNum=" + pageObject.getPerPageNum();
	}
	
	// 등급변경
	@PostMapping("/changeGradeNo.do")
	public String changeGradeNo(PageObject pageObject, MemberVO vo) throws Exception{
		
		// DB에서 등급을 변경 시킨다.
		service.changeGradeNo(vo);
		
		return "redirect:view.do?id=" + vo.getId() + "&page=" + pageObject.getPage() + "&perPageNum=" + pageObject.getPerPageNum();
	}
	
}

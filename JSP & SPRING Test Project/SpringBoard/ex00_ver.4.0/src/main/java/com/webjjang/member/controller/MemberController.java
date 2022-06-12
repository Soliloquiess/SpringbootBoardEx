package com.webjjang.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webjjang.member.service.MemberService;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.member.vo.MemberVO;

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
	//post로 처리(post매핑) url매핑은 login.do
	@PostMapping("/login.do")
	// 사용자가 아이디와 비밀번호를 입력해서 보낸다. -> 받는다.
	public String login(LoginVO invo, HttpSession session) throws Exception  {
		//로그인 정보를 db와 해서 dispatcher서블릿에서 서버에서 사용한 세션을 파라미터로 넘겨주고 아이디에 정보 집어넣으면 여기 집어넣으면 jsp에서 세션 정보를 사용 가능하다.
		log.info("로그인 처리 - invo : " + invo);
		
		session.setAttribute("login", service.login(invo));
		
		// 원래는 main으로 보내야하나 main을 안 만들어서 만들어진 게시판으로 임시로 보낸다.
		return "redirect:/board/list.do";
	}
	
	// 로그아웃 처리(제일 쉬움 그냥 세션만 지우면 된다.)
	@GetMapping("/logout.do")
	public String logout(HttpSession session) throws Exception {
		// 로그아웃 처리 - session의 정보를 지운다.
		session.removeAttribute("login");
		
		log.info("로그아웃 처리됨");
		
		// 원래는 main으로 보내야하나 main을 안 만들어서 만들어진 게시판으로 임시로 보낸다.
		return "redirect:/board/list.do";
	}
	
	// 회원가입 폼
	@GetMapping("/write.do")
	public String writeForm() throws Exception{
		return "member/write";
	}
	
	// 회원가입 처리
	public String write(MemberVO vo) throws Exception {
		
		// 회원 가입 처리
		service.write(vo);
		
		return "redirect:/member/login.do";
	}
	
}

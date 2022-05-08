package aloha.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	//메인페이지
	@GetMapping("/")
	public @ResponseBody String home() throws Exception{
		return "aloha campus";
	}
	
	//비회원페이지
	@GetMapping("/guest/welcome")
	public  String guest(){
		return  "guest/welcome";
	}
	//회원페이지
	@GetMapping("/member/welcome")
	public  String member(Principal principal , Model model){ //로그인 했을 떄 인증된 정보들 가져오기 가능(principal사용시)
		
		String userId= principal.getName();	//인증된 사용자 id를 가져옴.
		
		model.addAttribute("userId",userId);	//인증된 사용자를 등록
		
		return "member/welcome";
	}
	//어드민 회원페이지
	@GetMapping("/admin/welcome")
	public String admin(Principal principal , Model model) {
		
		String userId= principal.getName();	//인증된 사용자 id를 가져옴.
		
		model.addAttribute("userId",userId);	//인증된 사용자를 등록
		
		
		return "admin/welcome";
	}
	//로그인 페이지
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "auth/loginForm";
	}
	//로그인 에러 페이지
	@GetMapping("/auth/loginError")
	public String loginError() {
		return "auth/loginError";
	}
	
	
}

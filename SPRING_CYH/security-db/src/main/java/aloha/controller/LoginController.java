package aloha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

	// custom 로그인 페이지
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "auth/loginForm";
	}
	
	// custom 로그인 에러 페이지
	@RequestMapping("/loginError")
	public String loginError() {
		return "auth/loginError";
	}
	
	// custom 접근 거부 페이지
	@RequestMapping("/accessError")
	public String accessError(Model model) {
		
		model.addAttribute("msg", "접근이 제한되었습니다.");
		
		return "auth/accessError";
	}
}

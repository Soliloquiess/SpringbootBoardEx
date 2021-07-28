package aloha.controller;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

	// custom 로그인 페이지
	@RequestMapping("/loginForm")
	public String loginForm(Model model, @CookieValue(value="userId", required=false) Cookie cookie) {
		
		if(cookie!=null) {
			model.addAttribute("userId", cookie.getValue());	//쿠키로 부터 밸류값 가져옴(아이디 저장)
			model.addAttribute("rememberId", true);				//쿠키 저장 여부
		}
		
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

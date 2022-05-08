package aloha.controller;

import org.springframework.stereotype.Controller;
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
	public  String member(){
		return "member/welcome";
	}
	//어드민 회원페이지
	@GetMapping("/admin/welcome")
	public String admin() {
		return "admin/welcome";
	}
}

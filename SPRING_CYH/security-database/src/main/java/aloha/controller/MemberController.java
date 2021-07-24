package aloha.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import aloha.domain.Member;
import aloha.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController {
	
	// logger
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 회원가입 화면
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Model model, Member member) throws Exception {
		log.info("회원가입 화면");
		
	}
	
	// 회원가입 처리
	@RequestMapping( value = "/register", method = RequestMethod.POST)
	public String register(Model model, Member member, RedirectAttributes rttr) throws Exception{
		
		// 비밀번호 암호화
		String inputPassword = member.getUserPw();
		member.setUserPw(passwordEncoder.encode(inputPassword));	//입력된 평문의 패스워드를 암호화 해서 넘겨줌.
		
		service.register(member);
		
		rttr.addFlashAttribute("userName", member.getUserName());
		
		return "redirect:/user/success";	//성공시 석세스 페이지로 넘어감.
	}
	
	// 회원가입 성공 화면
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public void success(Model model) throws Exception {
		
	}
	
	// 회원목록 화면
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		model.addAttribute("list", service.list());
	}
	
	@PostMapping("/checkUserId")
	@ResponseBody
	public boolean checkUserId(String userId) throws Exception{
		
		boolean check = service.checkUserId(userId);
		return check;
	}
}




















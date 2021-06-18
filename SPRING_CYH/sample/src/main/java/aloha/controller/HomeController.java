package aloha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String home(Model model)throws Exception{
		model.addAttribute("data","안녕하세요");
		return "index";
	}
}

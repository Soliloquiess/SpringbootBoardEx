package org.hdcd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/login")
	public String loginForm(String error, String logout, Model model) {
		logger.info("error: " + error);
		logger.info("logout: " + logout);
		
		if (error != null) {
			model.addAttribute("error", "Login Error!!!");
		}

		if (logout != null) {
			model.addAttribute("logout", "Logout!!!");
		}

		return "views/loginForm";
	}

	@RequestMapping("/logout")
	public String logoutForm() {
		logger.info("logoutForm");
		
		return "views/logoutForm";
	}
}

package com.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class Login {


    @GetMapping("/login")
    public String doLogin(){
        return "/login/loginForm";
    }

    @GetMapping("/member_join")
    public String doMember_join(){
        return "/member/member_join";
    }


}

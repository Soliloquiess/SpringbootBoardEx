package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class First {

    @GetMapping("")
    public String doFirst(HttpServletRequest request, Model model){

        String strRole = "";
        String strAuth = "N";

        HttpSession session = request.getSession();
        if(session != null) {
            strRole = (String) session.getAttribute("ss_role");
        }
        if(strRole!=null && "ADMIN".equals(strRole)){
            strAuth = "Y";
        }

        System.out.println("strAuth : "+ strAuth);
        model.addAttribute("ss_auth", strAuth);

        return "/home/home";
    }
}

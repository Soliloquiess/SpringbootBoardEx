package com.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class Home {

    @GetMapping("")
    public String doHome(){

        return "/home/home";
    }

    @GetMapping("/study_reg")
    public String doStudy_reg(){


        return "/study/study_reg";
    }

    @GetMapping("/member_list")
    public String doMember_list(){
        return "/member/member_list";
    }
}

package com.spring.boot.controller;

import com.spring.boot.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class Home {

    @Autowired
    StudyService studyService;

    @GetMapping("")
    public String doHome(){

        return "/home/home";
    }

    @GetMapping("/study_reg")
    @ResponseBody
    public String doStudy_reg(){
            List<Map<String,String>> list = new ArrayList<>();
            list = studyService.doStudyList();

        //xml에서 설정 안하면 디비 있는 이름 그대로 써야
            for (Map<String, String> map : list){
                System.out.println(map.get("KEY_ID"));
                System.out.println(map.get("STUDY_DAY"));
                System.out.println(map.get("CONTENTS"));
                System.out.println(map.get("REG_DAY"));
            }

            return "--";

    }





    @GetMapping("/member_list")
    public String doMember_list(){
        return "/member/member_list";
    }
}

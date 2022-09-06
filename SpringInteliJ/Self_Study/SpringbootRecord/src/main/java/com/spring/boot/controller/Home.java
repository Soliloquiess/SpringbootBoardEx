package com.spring.boot.controller;

import com.spring.boot.service.StudyService;
import com.spring.boot.vo.Vo_study;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class Home {

    @Autowired
    StudyService studyService;

    @GetMapping("")
    public String doHome(){

        return "/home/home";
    }

    @GetMapping("/study_reg")
//    @ResponseBody
    public String doStudy_reg(HttpServletRequest request, Model model){  //이 리퀘스트 인자 받아서 (사용자로부터 넘어온걸 리스트에 담아서 던짐)
            List<Vo_study> list = new ArrayList<>();
            list = studyService.doStudyList();

        System.out.println("vo_study");
        for(Vo_study vo_study : list){
            System.out.println(vo_study.getKeyId());
            System.out.println(vo_study.getStudyDay());
            System.out.println(vo_study.getContents());
            System.out.println(vo_study.getRegDay());
        }

//        resultmap은 우리가 vo쓸때는 하나하나 매핑했는데 만약 xml에서 keyid가 아닌 keyid2라는 이런거 써야함.
//        이런걸 만들지 않고 쓰려면

       //xml에서 설정 안하면 디비 있는 이름 그대로 써야
//            for (Map<String, String> map : list){
//            for (Map<String, String> map : list){
//                System.out.println(map.get("KEY_ID"));
//                System.out.println(map.get("STUDY_DAY"));
//                System.out.println(map.get("CONTENTS"));
//                System.out.println(map.get("REG_DAY"));
//            }
    //리턴의 값은 포워드라 리퀘스트는 뷰까지 넘어가게됨
//            request.setAttribute("list",list);    //리퀘스트 객체에 담아서 리턴
            model.addAttribute("list", list);   //리퀘스트 객체 대신 모델에 담아서 리턴
//            return "--";
            return "/study/study_reg";
    }





    @GetMapping("/member_list")
    public String doMember_list(){
        return "/member/member_list";
    }
}

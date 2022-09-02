package com.spring.boot.controller;

import com.spring.boot.service.MemberService;
import com.spring.boot.service.StudyService;
import com.spring.boot.vo.Vo_member;
import com.spring.boot.vo.Vo_study;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
public class Con_member {

    @Autowired
    MemberService memberService;

    /* 전체 멤버 조회 */
    @GetMapping("/list")
    public String doMemberList(Model model){

        List<Vo_member> list = new ArrayList<>();
        list = memberService.doMemberList();

        model.addAttribute("list", list);


        return  "/member/member_list";
    }

    /* 입력 */
    @GetMapping("/insert")
    public String doIns(){
        //System.out.println("Controller - insert");
        return "/member/member_join";
    }

    /* 등록하기 실행  */
    @PostMapping("/insert_exe")
    public String doInsExe(@ModelAttribute Vo_member vo_member){

        int intI = memberService.doMemberIns(vo_member);

        return "redirect:/member/list";
    }

    /* 수정 - 클릭 */
    @GetMapping("/modify")
    public String doMod(HttpServletRequest request){
        String strMemberId = request.getParameter("member_id");

        Vo_member vo_member = memberService.doMemberListOne(strMemberId);

        request.setAttribute("vo_member", vo_member);

        return "/member/member_mod";
    }

    /* 수정 - 처리  */
    @PostMapping("/modify_exe")
    public String doModExe(@ModelAttribute Vo_member vo_member){

        int intI = memberService.doMemberUp(vo_member);

        return "redirect:/home/member_reg";
    }


    /* 삭제 */
    @GetMapping("/delete")
    public String doDel(@RequestParam(value="key_id", defaultValue = "--") String strMemberId){

        int intI = memberService.doMemberDel(strMemberId);
        log.info("intI => "+intI);
        return "redirect:/member/list";
    }

}

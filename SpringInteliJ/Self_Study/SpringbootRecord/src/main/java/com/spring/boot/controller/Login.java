package com.spring.boot.controller;

import com.spring.boot.service.MemberService;
import com.spring.boot.vo.Vo_member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class Login {

    @Autowired
    MemberService memberService;

    @GetMapping("/login")
    public String doLogin(){
        return "/login/loginForm";
    }


    @PostMapping("/login_exe")
    public String doLoginExe(@RequestParam(value="loginId", defaultValue = "--") String strLoginId,
                             @RequestParam(value="password", defaultValue = "--") String strPassword,
                             Model model,
                              HttpServletRequest request){

        String strReturn ="";
        String strMessage="";
        boolean bl_login = false;   //로그인 했니 안했니 확인

        Vo_member vo_member = memberService.doMemberLogin(strLoginId);

        if(vo_member==null){
            System.out.println("d없음");
            strReturn = "/login/loginForm";
            strMessage = "ID가 존재하지 않아요.";
        }else{
            if(!strPassword.equals(vo_member.getPassword())) {
                strReturn = "/login/loginForm";
                strMessage = "패스워드가 일치하지 않아요.";
            }//else 없으면 패스워드 없어도 성공해서 수정
            else{

                bl_login = true;    //true 면 로그인 된거
                strReturn = "redirect:/";
                strMessage="login성공";
            }

        }

        model.addAttribute("message", strMessage);
        HttpSession session = request.getSession();
        if(bl_login == true){
            session.setAttribute("ss_member_id", vo_member.getMemberId());
            session.setAttribute("ss_login_id", vo_member.getLoginId());
            session.setAttribute("ss_name", vo_member.getName());
            session.setAttribute("ss_role", vo_member.getRole());
        }

        //System.out.println(vo_member.getName());
        return strReturn;
    }

    @GetMapping("/member_join")
    public String doMember_join(){
        return "/member/member_join";
    }

    @GetMapping("/logout")
    public String doLogout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        session.invalidate();

        return "redirect:/";
    }

}

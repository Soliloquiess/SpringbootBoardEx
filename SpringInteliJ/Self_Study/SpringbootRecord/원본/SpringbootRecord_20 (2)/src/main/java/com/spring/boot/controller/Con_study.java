package com.spring.boot.controller;

import com.spring.boot.service.StudyService;
import com.spring.boot.vo.Vo_study;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/study")
@Slf4j
public class Con_study {

    @Autowired
    StudyService studyService;

    @GetMapping("/list")
    public String doStudy_reg(HttpServletRequest request, Model model){

        List<Vo_study> list = new ArrayList<>();
        list = studyService.doStudyList();

            /*
            log.info("vo_study");
            for(Vo_study vo_study : list){
                log.info(vo_study.getKeyId());
                log.info(vo_study.getStudyDay());
                log.info(vo_study.getContents());
                log.info(vo_study.getRegDay());
            }
            */
        request.setAttribute("list", list);
        //   model.addAttribute("list", list);

        return "/study/study_reg";
    }

    /* 입력 */
    @GetMapping("/insert")
    public String doIns(){
        return "/study/study_ins";
    }

    /* 등록하기 실행  */
    @PostMapping("/insert_exe")
    public String doInsExe(@ModelAttribute Vo_study vo_study){

        int intI = studyService.doStudyIns(vo_study);

        return "redirect:/study/list";
    }

    /* 수정 */
    @GetMapping("/modify")
    public String doMod(HttpServletRequest request){
        String strKeyId = request.getParameter("key_id");

        Vo_study vo_study = new Vo_study();
        vo_study = studyService.doStudyListOne(strKeyId);

        request.setAttribute("vo_study", vo_study);

        return "/study/study_mod";
    }

    /* 수정 */
    @PostMapping("/modify_exe")
    public String doModExe(@ModelAttribute Vo_study vo_study){

        int intI = studyService.doStudyUp(vo_study);

        return "redirect:/study/list";
    }


    /* 삭제 */
    @GetMapping("/delete")
    public String doDel(@RequestParam(value="key_id", defaultValue = "--") String strKeyId){

        int intI = studyService.doStudyDel(strKeyId);
        log.info("intI => "+intI);
        return "redirect:/study/list";
    }


}

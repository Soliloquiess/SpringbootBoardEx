package com.spring.boot.service;

import com.spring.boot.dao.StudyDao;
import com.spring.boot.vo.Vo_study;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudyService {

    @Autowired
    StudyDao studyDao;

    public List<Vo_study> doStudyList(){

        List<Vo_study> list = new ArrayList<>();
        list = studyDao.doStudyList();
        return  list;
    }

    /* One Row Select */
    public Vo_study doStudyListOne(String strKeyId){
        Vo_study vo_study = new Vo_study();

        vo_study = studyDao.doStudyListOne(strKeyId);
        return  vo_study;
    }

    /* 공부기록 수정 */
    public int doStudyUp(Vo_study vo_study){

        int intI = studyDao.doStudyUp(vo_study);

        return  intI;
    }

    /* 공부기록 삭제 */
    public int doStudyDel(String strKeyId){

        int intI  = studyDao.doStudyDel(strKeyId);
        return  intI;
    }

    /* 공부하기 등록 */
    public int doStudyIns(Vo_study vo_study){

        int intI = studyDao.doStudyIns(vo_study);

        return  intI;
    };


}

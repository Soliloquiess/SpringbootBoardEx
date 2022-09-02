package com.spring.boot.service;

import com.spring.boot.dao.MemberDao;
import com.spring.boot.dao.StudyDao;
import com.spring.boot.vo.Vo_member;
import com.spring.boot.vo.Vo_study;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberDao memberDao;

    /* 멤버 리스트 */
    public List<Vo_member> doMemberList(){

        List<Vo_member> list = new ArrayList<>();
        list = memberDao.doMemberList();
        return  list;
    }

    /* One Row Select */
    public Vo_member doMemberListOne(String strMemberId){
        Vo_member vo_member = memberDao.doMemberListOne(strMemberId);
        return  vo_member;
    }

    /* 멤버 수정 */
    public int doMemberUp(Vo_member vo_member){

        int intI = memberDao.doMemberUp(vo_member);

        return  intI;
    }

    /* 공부기록 삭제 */
    public int doMemberDel(String strMemberId){

        int intI  = memberDao.doMemberDel(strMemberId);
        return  intI;
    }

    /* 공부하기 등록 */
    public int doMemberIns(Vo_member vo_member){

        int intI = memberDao.doMemberIns(vo_member);

        return  intI;
    };

    public Vo_member doMemberLogin(String strLoginId) {

        Vo_member vo_member = memberDao.doMemberLogin(strLoginId);

        return vo_member;

    }


}

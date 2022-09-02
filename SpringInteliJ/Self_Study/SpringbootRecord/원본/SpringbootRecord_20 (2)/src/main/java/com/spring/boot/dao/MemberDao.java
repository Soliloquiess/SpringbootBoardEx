package com.spring.boot.dao;

import com.spring.boot.vo.Vo_member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {

    // 공부기록 전체 리스트
    public List<Vo_member> doMemberList();

    // 공부기록 One row
    public Vo_member doMemberListOne(String strKeyId);

    // 공부기록 수정
    public int doMemberUp(Vo_member vo_member);

    // 공부기록 삭제
    public int doMemberDel(String strKeyId);

    // 공부하기 등록
    public int doMemberIns(Vo_member vo_member);

    // 로그인 부분
    Vo_member doMemberLogin(String strLoginId);
}

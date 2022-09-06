package com.spring.boot.dao;

import com.spring.boot.vo.Vo_study;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudyDao {

    // 공부기록 전체 리스트
    public List<Vo_study> doStudyList();

    // 공부기록 One row
    public Vo_study doStudyListOne(String strKeyId);

    // 공부기록 수정
    public int doStudyUp(Vo_study vo_study);

    // 공부기록 삭제
    public int doStudyDel(String strKeyId);

    // 공부하기 등록
    public int doStudyIns(Vo_study vo_study);

}

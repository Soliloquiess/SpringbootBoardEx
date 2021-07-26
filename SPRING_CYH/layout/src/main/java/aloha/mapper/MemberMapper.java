package aloha.mapper;

import java.util.List;


import aloha.domain.Member;

public interface MemberMapper {
	
	// 회원정보 등록
	public void create(Member member) throws Exception;
	
	// 회원정보 목록 조회
	public List<Member> list() throws Exception;
	
	// 회원 중복 체크
	public int checkUserId(String userId) throws Exception; 
}

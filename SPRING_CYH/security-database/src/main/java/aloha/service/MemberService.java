package aloha.service;

import java.util.List;

import aloha.domain.Member;

public interface MemberService {
	
	// 회원정보 등록
	public void register(Member member) throws Exception;
	
	// 회원정보 목록조회
	public List<Member> list() throws Exception;

	//
	public boolean checkUserId(String userId) throws Exception;	
	
 }

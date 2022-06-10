package com.webjjang.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.webjjang.member.mapper.MemberMapper;
import com.webjjang.member.vo.LoginVO;

@Service
public class MemberService {

	// 자동 DI
	@Inject
	private MemberMapper mapper;
	
	// 로그인
	public LoginVO login(LoginVO invo) throws Exception{
		return mapper.login(invo);
	}
}

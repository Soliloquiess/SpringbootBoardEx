package aloha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aloha.domain.Member;
import aloha.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;

	@Override
	public void register(Member member) throws Exception {
		// 회원정보 등록
		mapper.create(member);
		
		// 회원권한 등록
	}

	@Override
	public List<Member> list() throws Exception {
		return mapper.list();
	}

	@Override
	public boolean checkUserId(String userId) throws Exception {
		// db에서 전달받은 값이 true/false는 없으니까 매퍼는 true/false가 안됨
		 int cnt = mapper.checkUserId(userId);
		 if (cnt>0) return false;	//id 중복
		 
		 return true;
	}
	
	
	
	
}

package com.member.service;

import com.member.dao.MemberDAO;
import com.member.vo.MemberVO;

public class MemberViewService {
	public MemberVO service(String id) throws Exception{
		//생성 호출: controller - [service] - DAO
		
		MemberDAO dao = new MemberDAO();
		//list - view일때만 조회수 1 증가시킨다.
		//update와 update끝나고 view로 갈 때 조회수 1 증가 시키지 않는다.
		
		return dao.view(id); 
	}
}

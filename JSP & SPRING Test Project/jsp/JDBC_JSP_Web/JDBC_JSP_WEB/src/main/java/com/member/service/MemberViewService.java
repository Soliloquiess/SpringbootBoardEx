package com.member.service;

import com.member.dao.MemberDAO;
import com.member.vo.MemberVO;

public class MemberViewService {

	public MemberVO service(String id) throws Exception {
		// 생성 호출 :Controller - [Service] - DAO
		MemberDAO dao = new MemberDAO();
		return dao.view(id);
	}
	
}
